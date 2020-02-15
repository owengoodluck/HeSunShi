package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.SysRole;

public interface SysRoleDao {

	// 根据角色id查询菜单id字符串
	List<String> findMenuIdByRoleIds(@Param("roleIds") String[] roleIds);

	// 根据角色id查询角色名 
	List<String> findRoleNameByRoleIds(@Param("roleIds") String[] roleIds);

	// 查询角色用于分配角色时的下拉框
	List<SysRole> findRole();

	// 添加角色
	public int insertRole(SysRole role);

	// 分页查询角色信息
	public List<SysRole> queryRoleByPage(@Param("roleName") String roleName);

	// 修改角色
	public int updateRole(SysRole role);

	// 删除角色
	public int deleteRole(String id);

	// 获取角色已有权限(回显树)
	public String getRoleEmpower(String id);

	// 给角色赋权
	public int roleEmpower(SysRole role);

	//批量启用角色
	public int enableRoles(@Param("roleIds") String[] roleIds);

	//批量解冻用户
	public int disableRoles(@Param("roleIds") String[] roleIds);
}
