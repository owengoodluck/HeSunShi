package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.OrderDetailVO;

public interface OrderDetailDao {

	// 根据订单id查询订单详情
	List<OrderDetailVO> findOrderDetailByOrderId(@Param("orderId") String orderId);

	// 根据订单详情id查询订单详情
	OrderDetailVO findOrderDetailByDetailId(@Param("orderDetailId") String orderDetailId);

	// 改价
	void updatePrice(OrderDetailVO orderDetailVO);

	// 根据订单id查询通知id
	String findFormIdByOrderId(@Param("orderId") long orderId);

}
