package com.xseagull.boxing.features.dao;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CustomOrderDetail;

import java.util.List;

public interface OrderDetailDao {
	// 添加订单详情
	public int insert(CustomOrderDetail customOrderDetail);

	// 修改订单详情状态
	int updateStatusById(@Param("orderDetailId") long orderDetailId, @Param("status") int status);

	public int findNotRefundSum(@Param("orderDetailId") long orderDetailId);

	public long findOrderIdById(@Param("orderDetailId") long orderDetailId);

	public List<CustomOrderDetail> findByOrderId(@Param("orderId") long orderDetailId);

	//获取已付定金的list
	public List<CustomOrderDetail> getCompleteAdvancePaymentList();

	//获取已下单的list
	public List<CustomOrderDetail> getCompleteAllPaymentList();

}
