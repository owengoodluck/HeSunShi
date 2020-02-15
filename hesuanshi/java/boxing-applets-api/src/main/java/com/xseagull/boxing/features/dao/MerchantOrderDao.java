package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.MerchantOrder;
import com.xseagull.boxing.features.vo.MerchantOrderVO;
import com.xseagull.boxing.features.vo.SettlementDataVO;

/**
 * 商户订单管理
 */
public interface MerchantOrderDao {

	/**
	 * 根据不同状态查询商户订单
	 * 
	 * @param merchantOrderStatus
	 * @param userOrderStatus
	 * @param userId
	 * @return
	 */
	public List<MerchantOrderVO> selectMerchantOrderByStatus(@Param("merchantOrderStatus") int merchantOrderStatus,
			@Param("userOrderStatus") int userOrderStatus, @Param("merchantId") long merchantId);

	/**
	 * 根据订单编号查询订单详情
	 * 
	 * @param orderNumber
	 * @return
	 */
	public MerchantOrderVO findOrderDetailByMerchantOrderId(long merchantOrderId);

	/**
	 * 确认接单
	 * 
	 * @param id
	 */
	public void confirm(long id);

	/**
	 * 驳回订单
	 * 
	 * @param id
	 * @param reason
	 */
	public void reject(@Param("id") long id, @Param("reason") String reason);

	/**
	 * 备注订单
	 * 
	 * @param id
	 * @param remark
	 */
	public void remark(@Param("id") long id, @Param("remark") String remark);

	/**
	 * 查询商户结算页面数据
	 * 
	 * @param merchantId
	 * @return
	 */
	public SettlementDataVO selectSettlementData(long merchantId);

	/**
	 * 根据状态查询商户订单
	 * 
	 * @param status
	 * @return
	 */
	public List<MerchantOrder> findMerchantOrderByStatus(@Param("status") int status);

	/**
	 * 超时未处理,自动驳回
	 * 
	 * @param id
	 * @param status
	 * @param reason
	 */
	public void updateStatus(@Param("id") long id, @Param("status") int status, @Param("reason") String reason);

}
