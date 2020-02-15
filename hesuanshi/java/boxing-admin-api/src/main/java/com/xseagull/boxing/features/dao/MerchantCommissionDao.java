package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.MerchantCommissionVO;

/**
 * 商户佣金记录
 */
public interface MerchantCommissionDao {
	/**
	 * 查询商户佣金记录
	 * 
	 * @param nickName
	 * @return
	 */
	public List<MerchantCommissionVO> findCommissionByMerchantId(long merchatId);

	/**
	 * 修改佣金记录状态
	 * 
	 * @param withdrawId
	 * @param status
	 */
	public void updateStatus(@Param("withdrawId") long withdrawId, @Param("status") int status);

}
