package com.xseagull.boxing.features.dao;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.UserCompany;

public interface UserCompanyDao {

	/**
	 * 根据用户id查询用户企业信息
	 * 
	 * @param userId
	 * @return
	 */
	public UserCompany getUserCompanyDetail(@Param("userId") long userId);

	/**
	 * 新增企业信息
	 * 
	 * @param userCompany
	 */
	public void insert(UserCompany userCompany);

	/**
	 * 修改企业信息
	 * 
	 * @param userCompany
	 */
	public void update(UserCompany userCompany);

}
