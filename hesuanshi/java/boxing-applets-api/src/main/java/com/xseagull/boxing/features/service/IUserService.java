package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.entity.UserCompany;

/**
 * 用户管理
 */
public interface IUserService {
	// 根据用户id查询用户信息
	public ResultData getUserDetail(String userId);

	// 更新用户信息
	public ResultData update(User user, String userId);

	// 绑定手机号
	public ResultData updatPhone(String userId, String phone, String code);

	// 根据用户id查询用户企业信息
	public ResultData getUserCompanyDetail(long userId);

	// 保存用户企业信息
	public ResultData saveUserCompany(UserCompany userCompany);

	// 我邀请的用户列表
	public ResultData findMyUser(Integer pageNo, Integer pageSize, long userId);
}
