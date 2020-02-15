package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.MerchantWithdrawVO;

/**
 * 商户提现记录
 */
public interface MerchantWithdrawDao {
	/**
	 * 查询商户提现记录
	 * 
	 * @param nickName
	 * @return
	 */
	public List<MerchantWithdrawVO> findWithdrawByMerchantId(long merchatId);

	/**
	 * 查询所有商户提现记录
	 * 
	 * @param name
	 * @param status
	 * @return
	 */
	public List<MerchantWithdrawVO> queryWithdrawByPage(@Param("name") String name, @Param("status") Integer status);

	/**
	 * 修改提现状态
	 * @param id
	 * @param status
	 */
	public void updateStatus(@Param("id") long id, @Param("status") int status);

}
