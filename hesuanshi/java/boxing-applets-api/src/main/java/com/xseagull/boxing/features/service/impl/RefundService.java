package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.dao.OrderDetailDao;
import com.xseagull.boxing.features.dao.RefundDao;
import com.xseagull.boxing.features.entity.Refund;
import com.xseagull.boxing.features.entity.RefundFile;
import com.xseagull.boxing.features.service.IRefundService;
import com.xseagull.boxing.features.vo.RefundVO;

@Service
@Transactional
public class RefundService implements IRefundService {

	@Autowired
	private RefundDao refundDao;

	@Autowired
	private OrderDetailDao orderDetailDao;
	
	@Autowired
	private OrderDao orderDao;

	/**
	 * 提交退款
	 */
	@Transactional
	@Override
	public ResultData refundSub(Refund refund) {
		refund.setRefundNumber(String.valueOf(System.currentTimeMillis()));
		// 新增退款记录
		refundDao.insertRefund(refund);
		List<String> imgList = JSONArray.parseArray(refund.getImages(), String.class);
		if (!imgList.isEmpty() || imgList != null) {
			for (String string : imgList) {
				RefundFile refundFile = new RefundFile();
				refundFile.setRefundId(refund.getId());
				refundFile.setResource(string);
				refundDao.insertRefundFile(refundFile);
			}
		}
		orderDetailDao.updateStatusById(refund.getOrderDetailId(), 2);
		// 根据商品详情id查询该订单是否多条商品有存在退款
		int i = orderDetailDao.findNotRefundSum(refund.getOrderDetailId());
		if (i == 0) {
			//说明该订单的商品全部都是退款的-->把订单改为取消订单
			//根据订单详情id查询订单id
			long orderId = orderDetailDao.findOrderIdById(refund.getOrderDetailId());
			orderDao.updateOrderStatus(orderId, 4);
		}
		return ResultData.success(null);
	}

	/**
	 * 查询用户退款列表
	 */
	@Override
	public ResultData findRefundList(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<RefundVO> list = refundDao.findRefundList(userId);
		PageInfo<RefundVO> page = new PageInfo<RefundVO>(list);
		return ResultData.success(page);
	}

}
