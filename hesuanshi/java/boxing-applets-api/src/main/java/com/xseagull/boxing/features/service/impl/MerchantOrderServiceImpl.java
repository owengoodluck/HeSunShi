package com.xseagull.boxing.features.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.ExpressDao;
import com.xseagull.boxing.features.dao.MerchantCommissionDao;
import com.xseagull.boxing.features.dao.MerchantOrderDao;
import com.xseagull.boxing.features.dao.MerchantWithdrawDao;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.dao.OrderExpressDao;
import com.xseagull.boxing.features.entity.MerchantCommission;
import com.xseagull.boxing.features.entity.MerchantWithdraw;
import com.xseagull.boxing.features.entity.OrderExpress;
import com.xseagull.boxing.features.service.IMerchantOrderService;
import com.xseagull.boxing.features.vo.MerchantOrderVO;
import com.xseagull.boxing.features.vo.OrderDetailVO;

@Service
@Transactional
public class MerchantOrderServiceImpl implements IMerchantOrderService {

	@Autowired
	private MerchantOrderDao merchantOrderDao;

	@Autowired
	private ExpressDao expressDao;

	@Autowired
	private OrderExpressDao orderExpressDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private MerchantCommissionDao merchantCommissionDao;

	@Autowired
	private MerchantWithdrawDao merchantWithdrawDao;

	/**
	 * 根据不同状态查询商户订单
	 */
	@Override
	public ResultData selectMerchantOrderByStatus(Integer pageNo, Integer pageSize, int merchantOrderStatus,
			int userOrderStatus, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<MerchantOrderVO> list = merchantOrderDao.selectMerchantOrderByStatus(merchantOrderStatus, userOrderStatus,
				userId);
		for (MerchantOrderVO merchantOrderVO : list) {
			List<OrderDetailVO> orderDetailVO = merchantOrderVO.getOrderDetailVO();
			for (OrderDetailVO orderDetailVO2 : orderDetailVO) {
				if (merchantOrderVO.getFareType() == 0) {
					merchantOrderVO.setCountPrice(merchantOrderVO.getCountPrice().add(orderDetailVO2.getPrice())
							.add(orderDetailVO2.getFare()));
				} else {
					merchantOrderVO.setCountPrice(merchantOrderVO.getCountPrice().add(orderDetailVO2.getPrice()));
				}
			}
		}
		PageInfo<MerchantOrderVO> page = new PageInfo<MerchantOrderVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 根据商户订单id查询订单详情
	 */
	@Override
	public ResultData findOrderDetailByMerchantOrderId(long merchantOrderId) {
		MerchantOrderVO merchantOrderVO = merchantOrderDao.findOrderDetailByMerchantOrderId(merchantOrderId);
		merchantOrderVO.setResourcePath("https://admin.cwzjapp.com/#/download/2/" + merchantOrderVO.getOrderNumber());
		List<OrderDetailVO> orderDetailVO = merchantOrderVO.getOrderDetailVO();
		for (OrderDetailVO orderDetailVO2 : orderDetailVO) {
			if (merchantOrderVO.getFareType() == 0) {
				merchantOrderVO.setCountPrice(
						merchantOrderVO.getCountPrice().add(orderDetailVO2.getPrice()).add(orderDetailVO2.getFare()));
			} else {
				merchantOrderVO.setCountPrice(merchantOrderVO.getCountPrice().add(orderDetailVO2.getPrice()));
			}
		}
		return ResultData.success(merchantOrderVO);
	}

	/**
	 * 确认接单
	 */
	@Override
	public ResultData confirm(long id, long userId) {
		merchantOrderDao.confirm(id);
		MerchantOrderVO orderVO = merchantOrderDao.findOrderDetailByMerchantOrderId(id);
		MerchantCommission merchantCommission = new MerchantCommission();
		merchantCommission.setMerchantId(userId);
		merchantCommission.setOrderId(orderVO.getOrderId());
		merchantCommission.setCommission(orderVO.getMoney());
		merchantCommissionDao.insert(merchantCommission);
		return ResultData.success(null);
	}

	/**
	 * 驳回订单
	 */
	@Override
	public ResultData reject(long id, String reason) {
		merchantOrderDao.reject(id, reason);
		return ResultData.success(null);
	}

	/**
	 * 备注订单
	 */
	@Override
	public ResultData remark(long id, String remark) {
		merchantOrderDao.remark(id, remark);
		return ResultData.success(null);
	}

	/**
	 * 查询快递列表
	 */
	@Override
	public ResultData getExpress() {
		return ResultData.success(expressDao.getExpress());
	}

	/**
	 * 发货
	 */
	@Override
	public ResultData orderSend(OrderExpress orderExpress) {
		orderExpressDao.insert(orderExpress);
		orderDao.updateOrderStatus(orderExpress.getOrderId(), 2);
		return ResultData.success(null);
	}

	/**
	 * 查询商户结算页面数据
	 */
	@Override
	public ResultData selectSettlementData(long userId) {
		return ResultData.success(merchantOrderDao.selectSettlementData(userId));
	}

	/**
	 * 商户可提现金额全部提现
	 * 
	 * @param money
	 * @param userId
	 * @return
	 */
	@Override
	public ResultData withdrawableAll(String money, long userId) {
		// 新增一条提现记录
		MerchantWithdraw merchantWithdraw = new MerchantWithdraw();
		merchantWithdraw.setMoney(new BigDecimal(money));
		merchantWithdraw.setMerchantId(userId);
		merchantWithdrawDao.insert(merchantWithdraw);
		merchantCommissionDao.updateStatusAndWithdrawRecordId(merchantWithdraw.getId(), userId);
		return ResultData.success(null);
	}

	/**
	 * 提现记录列表
	 */
	@Override
	public ResultData findTixianRecord(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<MerchantWithdraw> list = merchantWithdrawDao.findTixianRecord(userId);
		PageInfo<MerchantWithdraw> page = new PageInfo<MerchantWithdraw>(list);
		return ResultData.success(page);
	}

	/**
	 * 佣金记录列表
	 */
	@Override
	public ResultData findCommissionRecord(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<MerchantCommission> list = merchantCommissionDao.findCommissionRecord(userId);
		PageInfo<MerchantCommission> page = new PageInfo<MerchantCommission>(list);
		return ResultData.success(page);
	}

}
