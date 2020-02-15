package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.SysUser;

public interface SysUserDao {
	// 根据账号密码查询用户
	public SysUser queryUser(SysUser sysUser);

	// 分页查询系统用户
	public List<SysUser> queryUserByPage(@Param("name") String name);

	// 分配角色
	public int assignRole(SysUser user);

	// 添加系统用户
	public int insertUser(SysUser user);

	// 根据系统用户id查询信息
	public SysUser findSysUserByid(@Param("sysUserId") String sysUserId);

	// 根据部门ID查询部门下面的用户
	public List<SysUser> findSysUserByDeptId(@Param("deptId") String deptId);

	// 根据用户ID查询部门下面的用户
//	public List<JigouLeaguerVO> findDeptLeaguerByUserId(@Param("sysUserName") String sysUserName,
//			@Param("userId") String userId);
//
//	// 根据登入的机构人员所属部门id查询出该机构所有成员信息
//	public List<JigouLeaguerVO> findDeptLeaguerByDeptId(@Param("sysUserName") String sysUserName,
//			@Param("deptId") String deptId);
}
