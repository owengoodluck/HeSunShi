package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.User;

/**
 * 用户管理
 */
public interface UserDao {
	/**
	 * 分页查询用户信息
	 * 
	 * @param nickName
	 * @return
	 */
	public List<User> queryUserByPage(@Param("name") String name);

	/**
	 * 根据用户id查询openId
	 * 
	 * @param getuId
	 * @return
	 */
	public String findOpenidById(@Param("userId") long userId);

	/**
	 * 根据手机号码查询用户
	 * 
	 * @param phone
	 * @return
	 */
	public List<User> findUserByPhone(@Param("phone") String phone);

	/**
	 * 根据用户id查询邀请的用户列表
	 * 
	 * @param uId
	 * @return
	 */
	public List<User> findUserByRecommenderId(@Param("uId") long uId);

}
