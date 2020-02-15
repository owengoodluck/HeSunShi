package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Merchant;

/**
 * 商户
 */
public interface MerchantDao {
	/**
	 * 分页查询商户
	 * 
	 * @param nickName
	 * @return
	 */
	public List<Merchant> queryByPage(@Param("name") String name);

	/**
	 * 根据id修改推广人员的状态
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	public int updateStatus(@Param("id") long id, @Param("status") int status, @Param("remark") String remark);

	/**
	 * 查询所有通过审核的商户
	 * 
	 * @return
	 */
	public List<Merchant> getMerchant();

}
