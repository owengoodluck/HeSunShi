package com.xseagull.boxing.pay;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xseagull.boxing.constant.OrderPaymentStatusEnum;
import com.xseagull.boxing.constant.OrderStatusEnum;
import com.xseagull.boxing.constant.PaymentType;
import com.xseagull.boxing.features.dao.*;
import com.xseagull.boxing.features.entity.CustomOrderDetail;
import com.xseagull.boxing.features.service.IOrderPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CommissionRecord;
import com.xseagull.boxing.features.entity.Promoter;
import com.xseagull.boxing.features.entity.WalletRecord;
import com.xseagull.boxing.features.vo.CustomOrderVO;
import com.xseagull.boxing.features.vo.OrderDetailVO;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
public class PayService implements IPayService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	@Value("${serviceTemplateId}")
	private String serviceTemplateId;

	@Autowired
	private UserDao userDao;

	@Autowired
	private WalletDao walletDao;

	@Autowired
	private CommissionRecordDao commissionRecordDao;

	@Autowired
	private IOrderPayService orderPayService;

	/**
	 * 支付成功做处理
	 */
	@Override
	public ResultData orderNotify(String outTradeNo) {

		//1. 查询支付记录，并更新其状态
		OrderPay orderPay = orderPayService.findByOutTradeNo(outTradeNo);
		orderPayService.updatePaymentStatusByOutTradeNo(outTradeNo,1);//'0:"等待确认"  1:"成功"  2:"失败"'

		//2. 获取订单记录表
		String internalTradeNo = orderPay.getInternalTradeNo();
		CustomOrderVO customOrderVO = orderDao.findByOrderId(Long.valueOf(internalTradeNo));
		if (customOrderVO != null) {
			if (customOrderVO.getStatus().equals("0")) {
				if(PaymentType.ADVANCE.getValue().equals(orderPay.getPaymentType()+"")){
					orderDao.updatePayStatus(customOrderVO.getId(),OrderPaymentStatusEnum.COMPLETE_ADVANCE_PAYMENT.getValue());
				}else if(PaymentType.REST.getValue().equals(orderPay.getPaymentType()+"")){
					orderDao.updatePayStatus(customOrderVO.getId(), OrderPaymentStatusEnum.COMPLETE_REST.getValue());
				}else if(PaymentType.ALL.getValue().equals(orderPay.getPaymentType()+"")){
					orderDao.updatePayStatus(customOrderVO.getId(), OrderPaymentStatusEnum.COMPLETE_ALL.getValue());
				}else{
					throw new RuntimeException(outTradeNo+" ,Invalid PaymentType:"+orderPay.getPaymentType());
				}
				List<CustomOrderDetail> detailVO = this.orderDetailDao.findByOrderId(customOrderVO.getId());
				for (CustomOrderDetail orderDetailVO : detailVO) {
					Map<String, TemplateData> m = new HashMap<String, TemplateData>();
					m.put("first", new TemplateData("您有新的订单，请尽快安排生产处理"));
					m.put("keyword1", new TemplateData(customOrderVO.getContactName()));
					m.put("keyword2", new TemplateData(orderDetailVO.getTypeName()));
					m.put("keyword3", new TemplateData(orderDetailVO.getMaterialName()));
					m.put("keyword4", new TemplateData(orderDetailVO.getLength() + "cm*" + orderDetailVO.getWidth()
							+ "cm*" + orderDetailVO.getHeight() + "cm"));
					m.put("keyword5", new TemplateData(orderDetailVO.getPiece() + ""));
					List<String> list = userDao.findServiceNumber();
					for (String string : list) {
						ServiceMsgUtils.sendServiceNumberMsg(WeiChartUtils.getServiceNumberAccessToken().getAccess_token(),
								string, serviceTemplateId, m);
					}
				}
				try {
					// 异步生成佣金记录
					commissionRecord(customOrderVO);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else {
			WalletRecord walletRecord = walletDao.findWalletRecordByOutTradeNo(outTradeNo);
			if (walletRecord != null && walletRecord.getStatus() == 0) {
				walletDao.updateRecordStatus(outTradeNo, 1);
				walletDao.updateMoney(walletRecord.getMoney(),walletRecord.getUserId());
			}
		}
		return ResultData.success(null);
	}

	/**
	 * 生成佣金记录
	 * 
	 * @param customOrderVO
	 * @throws InterruptedException
	 */
	@Async
	public void commissionRecord(CustomOrderVO customOrderVO) throws InterruptedException {
		Promoter promoter = userDao.findRecommenderById(customOrderVO.getUserId());
		if (promoter != null && BigDecimal.valueOf(promoter.getCommissionRate()).compareTo(BigDecimal.ZERO) != 0) {
			List<OrderDetailVO> list = customOrderVO.getOrderDetailVO();
			for (OrderDetailVO orderDetailVO : list) {
				CommissionRecord commissionRecord = new CommissionRecord();
				commissionRecord.setUserId(promoter.getUserId());
				commissionRecord.setOrderDetailId(orderDetailVO.getId());
				commissionRecord.setPrice(orderDetailVO.getPrice());
				commissionRecord.setCommission(BigDecimal.valueOf(promoter.getCommissionRate())
						.multiply(orderDetailVO.getPrice()).divide(BigDecimal.valueOf(100)));
				commissionRecordDao.insert(commissionRecord);
			}
		}
	}

}
