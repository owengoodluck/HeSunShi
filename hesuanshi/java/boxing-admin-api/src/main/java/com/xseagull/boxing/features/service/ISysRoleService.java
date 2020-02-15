package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysRole;

public interface ISysRoleService {

	// 查询角色用于分配角色时的下拉框
	ResultData findRole();

	// 添加角色
	ResultData insertRole(SysRole role);

	// 查询角色
	ResultData queryRoleByPage(Integer pageNo, Integer pageSize, String roleName);

	// 修改角色
	ResultData updateRole(SysRole role);

	// 删除角色
	ResultData deleteRole(String id);
	
	//获取角色已有权限(回显树)
	ResultData getRoleEmpower(String id);
	
	//给角色赋权
	ResultData roleEmpower(SysRole role);

	//批量启用角色
	ResultData enableRoles(String roleIds);

	//批量禁用角色
	ResultData disableRoles(String roleIds);
}
