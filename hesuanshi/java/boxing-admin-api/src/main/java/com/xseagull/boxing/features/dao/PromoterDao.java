package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Promoter;

/**
 * 推广人员
 */
public interface PromoterDao {
	/**
	 * 分页查询推广人员
	 * 
	 * @param nickName
	 * @return
	 */
	public List<Promoter> queryByPage(@Param("name") String name);

	/**
	 * 修改佣金比例
	 * 
	 * @param promoter
	 * @return
	 */
	public int updateRate(Promoter promoter);

	/**
	 * 根据id修改推广人员的状态
	 * 
	 * @param id
	 * @param i
	 * @return
	 */
	public int updateStatus(@Param("id") long id, @Param("status") int status, @Param("remark") String remark);

}
