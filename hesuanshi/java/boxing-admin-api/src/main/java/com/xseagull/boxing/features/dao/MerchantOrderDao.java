package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.features.vo.MerchantOrderVO;

/**
 * 商户订单
 */
public interface MerchantOrderDao {

	// 根据订单id查询商户派单
	List<MerchantOrderVO> findMerchantOrderByOrderId(long orderId);

	// 新增商户订单记录
	public int insert(MerchantOrderVO merchantOrderVO);

}
