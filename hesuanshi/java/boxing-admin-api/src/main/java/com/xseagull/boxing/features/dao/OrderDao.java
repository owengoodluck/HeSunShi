package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.OrderVO;

public interface OrderDao {

	// 订单分页
	public List<OrderVO> queryByPage(@Param("status") String status, @Param("orderNumber") String orderNumber);

	public int updateStatus(@Param("orderId") long orderId, @Param("status") int status);

	public int queryTotalByOrderId(@Param("orderId") long orderId);

	public void updateStatusByorderDetailId(@Param("refundNumber") String refundNumber);

	public String selectOrderNumberById(long orderId);

	public List<OrderVO> queryByPageAndMerchantStatus(@Param("status") String status,
			@Param("merchantStatus") String merchantStatus, @Param("orderNumber") String orderNumber);

	public void updateMerchantStatus(@Param("id") long id, @Param("merchantStatus") int merchantStatus);

}
