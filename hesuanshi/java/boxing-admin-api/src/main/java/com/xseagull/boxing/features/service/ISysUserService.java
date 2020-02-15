package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysUser;

public interface ISysUserService {
	// 登录
	public ResultData queryUser(SysUser sysUser);

	// 分页查询系统用户
	public ResultData queryUserByPage(Integer pageNo, Integer pageSize, String name);

	// 分配角色
	public ResultData assignRole(SysUser user);

	// 新增系统用户
	public ResultData insertUser(SysUser user);

	// 新增普通机构成员
	public ResultData insertJigouLeaguer(SysUser user, String userId);
}
