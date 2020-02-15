package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.constant.OrderStatusEnum;
import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CustomOrder;
import com.xseagull.boxing.features.vo.CustomOrderVO;
import com.xseagull.boxing.features.vo.OrderNumVO;

/**
 * 订单管理
 */
public interface OrderDao {
	/**
	 * 添加订单
	 */
	public int insertOrder(CustomOrder customOrder);

	/**
	 * 根据商户订单号修改订单状态
	 * 
	 * @param outTradeNo
	 * @param status
	 */
	public void updateStatus(@Param("outTradeNo") String outTradeNo, @Param("status") int status);

	/**
	 * 根据状态查询用户订单
	 * 
	 * @param status
	 * @param userId
	 * @return
	 */
	public List<CustomOrderVO> findOrderByStatus(@Param("status") int status, @Param("userId") long userId);

	/**
	 * 根据微信交易编号查询订单详情
	 * 
	 * @param outTradeNo
	 * @return
	 */
	public CustomOrderVO findOrderDetailByOutTradeNo(@Param("outTradeNo") String outTradeNo);

	/**
	 * 根据修改订单状态
	 * 
	 * @param orderId
	 * @param status
	 */
	public void updateOrderStatus(@Param("orderId") long orderId, @Param("status") int status);
	
	
	/**
	 * 根据订单ID修改 支付状态
	 * @param orderId
	 * @param payStatus
	 */
	public void updatePayStatus(@Param("orderId") long orderId, @Param("payStatus") int payStatus);
	
	/**
	 * 根据订单ID修改 商户状态
	 */
	public void updateMerchantStatus(@Param("orderId") long orderId, @Param("merchantStatus") int merchantStatus);

	/**
	 * 确认收货
	 * 
	 * @param orderId
	 * @return
	 */
	public int updateOrderAndDetail(@Param("orderId") long orderId);

	/**
	 * 根据订单id修改商户交易编号
	 * 
	 * @param orderId
	 * @param string
	 */
	public void updateBusinessNumberById(@Param("orderId") long orderId, @Param("string") String string);

	/**
	 * 查询不同状态订单的条数
	 * 
	 * @param userId
	 * @return
	 */
	public OrderNumVO findOrderNum(@Param("userId") long userId);

	/**
	 * 根据状态查询所有订单
	 * 
	 * @param status
	 * @return
	 */
	public List<CustomOrderVO> findAllOrderByStatus(@Param("status") int status);

	/**
	 * 根据订单id查询收货信息
	 * 
	 * @param id
	 * @return
	 */
	public CustomOrderVO selectAddressById(long id);

	public CustomOrderVO findByOrderId(long orderId);

}
