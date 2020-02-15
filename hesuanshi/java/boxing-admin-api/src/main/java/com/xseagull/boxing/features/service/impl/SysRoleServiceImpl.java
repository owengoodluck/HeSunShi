package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.SysRoleDao;
import com.xseagull.boxing.features.entity.SysRole;
import com.xseagull.boxing.features.service.ISysRoleService;

@Service
@Transactional
public class SysRoleServiceImpl implements ISysRoleService {

	@Autowired
	private SysRoleDao sysRoleDao;

	// 查询角色用于分配角色时的下拉框
	@Override
	public ResultData findRole() {
		return ResultData.success(sysRoleDao.findRole());
	}

	// 添加角色
	@Override
	public ResultData insertRole(SysRole role) {
		return ResultData.success(sysRoleDao.insertRole(role));
	}

	// 查询角色
	@Override
	public ResultData queryRoleByPage(Integer pageNo, Integer pageSize, String roleName) {
		PageHelper.startPage(pageNo, pageSize);
		List<SysRole> list = sysRoleDao.queryRoleByPage(roleName);
		PageInfo<SysRole> page = new PageInfo<SysRole>(list);
		return ResultData.success(page);
	}

	// 修改角色
	@Override
	public ResultData updateRole(SysRole role) {
		return ResultData.success(sysRoleDao.updateRole(role));
	}

	// 删除角色
	@Override
	public ResultData deleteRole(String id) {
		return ResultData.success(sysRoleDao.deleteRole(id));
	}

	//获取角色已有权限(回显树)
	@Override
	public ResultData getRoleEmpower(String id) {
		String empower = sysRoleDao.getRoleEmpower(id);
		if (StringUtils.isEmpty(empower)) {
			empower = "1";
		}
		return ResultData.success(empower.split(","));
	}

	//给角色赋权
	@Override
	public ResultData roleEmpower(SysRole role) {
		return ResultData.success(sysRoleDao.roleEmpower(role));
	}

	/**
	 * 批量启用角色
	 */
	@Override
	public ResultData enableRoles(String roleIds) {
		return ResultData.success(sysRoleDao.enableRoles(roleIds.split(",")));
	}

	/**
	 * 批量禁用角色
	 */
	@Override
	public ResultData disableRoles(String roleIds) {
		return ResultData.success(sysRoleDao.disableRoles(roleIds.split(",")));
	}
}
