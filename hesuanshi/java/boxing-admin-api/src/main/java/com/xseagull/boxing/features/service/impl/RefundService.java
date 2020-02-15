package com.xseagull.boxing.features.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.OrderDetailDao;
import com.xseagull.boxing.features.dao.RefundDao;
import com.xseagull.boxing.features.service.IRefundService;
import com.xseagull.boxing.features.vo.OrderDetailVO;
import com.xseagull.boxing.features.vo.RefundFileVO;
import com.xseagull.boxing.features.vo.RefundVO;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ValidatorUtils;

@Service
@Transactional
public class RefundService implements IRefundService {

	@Value("${orderNotify}")
	private String orderNotify;

	@Autowired
	private RefundDao refundDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	/**
	 * 退款列表
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String status, String refundNumber,
			String userName) {
		PageHelper.startPage(pageNo, pageSize);
		List<RefundVO> list = refundDao.queryByPage(status, refundNumber, userName);
		PageInfo<RefundVO> page = new PageInfo<RefundVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 根据退款id查询附件
	 */
	@Override
	public ResultData findRefundFileById(String id) {
		List<RefundFileVO> list = refundDao.findRefundFileById(id);
		return ResultData.success(list);
	}

	/**
	 * 确认退款
	 */
	@Override
	public ResultData orderRefund(RefundVO refundVO) {
		// 根据订单详情id,查询所在订单的信息
		List<OrderDetailVO> list = orderDetailDao.findOrderDetailByOrderId(refundVO.getOrderId() + "");
		BigDecimal count = BigDecimal.ZERO;
		int fareType = list.get(0).getFareType();
		if (fareType == 0) {
			for (OrderDetailVO orderDetailVO : list) {
				count = count.add(BigDecimal.valueOf(orderDetailVO.getPrice()))
						.add(BigDecimal.valueOf(orderDetailVO.getFare()));
			}
		} else {
			for (OrderDetailVO orderDetailVO : list) {
				count = count.add(BigDecimal.valueOf(orderDetailVO.getPrice()));
			}
		}
		// 去掉小数后面的多余0
		String totralFee = ValidatorUtils.subZeroAndDot(count.multiply(new BigDecimal(100)).toString());
		String refundFee = ValidatorUtils.subZeroAndDot(refundVO.getMoney().multiply(new BigDecimal(100)).toString());
		// //将退款状态改为退款中
		refundDao.updateStatus(refundVO.getId(), 3);
		System.out.println(totralFee + "======" + refundFee);
		// 退款
		Map<String, String> refundWei = WeiChartUtils.refundWei(list.get(0).getBusinessNumber(),
				refundVO.getRefundNumber(), totralFee, refundFee, orderNotify + "refundWeiNotifyUrl");
		if (refundWei.get("result_code").equals("SUCCESS")) {
			return ResultData.success(null);
		} else {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, refundWei.get("err_code_des"));
		}
	}

}
