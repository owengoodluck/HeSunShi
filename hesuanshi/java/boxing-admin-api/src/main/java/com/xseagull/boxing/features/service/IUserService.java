package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;

/**
 * 用户管理
 */
public interface IUserService {
	// 分页查询用户信息
	public ResultData queryUserByPage(Integer pageNo, Integer pageSize, String nickName);

	public ResultData findUserByPhone(String phone);

	//根据用户id查询邀请的用户列表
	public ResultData findUserByRecommenderId(Integer pageNo, Integer pageSize, long uId);
}
