package com.xseagull.boxing.features.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.SysMenuDao;
import com.xseagull.boxing.features.dao.SysRoleDao;
import com.xseagull.boxing.features.dao.SysUserDao;
import com.xseagull.boxing.features.entity.SysUser;
import com.xseagull.boxing.features.service.ISysUserService;
import com.xseagull.boxing.security.AccessToken;
import com.xseagull.boxing.security.JwtTokenUtil;
import com.xseagull.boxing.util.EncryptUtils;

@Service
@Transactional
public class SysUserService implements ISysUserService {
	@Autowired
	private SysUserDao sysUserDao;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private SysRoleDao sysRoleDao;

	@Autowired
	private SysMenuDao sysMenuDao;

	/**
	 * 系统用户登录
	 */
	@Override
	public ResultData queryUser(SysUser sysUser) {
		sysUser.setPassWord(EncryptUtils.md5Encode(sysUser.getPassWord()));
		SysUser user = sysUserDao.queryUser(sysUser);
		if (user == null) {
			return ResultData.warn(ResultCode.PASSWORD_ERROR);
		} else {
			AccessToken accessToken = jwtTokenUtil.generateSysToken(user.getId());
			// 查询出这个用户有哪些权限
			if (StringUtils.isEmpty(user.getRoleIds())) {
				// 用户没有权限不给登录
				return ResultData.warn(ResultCode.NOT_EMPOWER);
			} else {
				String[] roleIds = user.getRoleIds().split(",");
				List<String> list = sysRoleDao.findMenuIdByRoleIds(roleIds);
				List<String> temp = new ArrayList<String>();
				if (list.isEmpty() || list.size() <= 0) {
					return ResultData.warn(ResultCode.NOT_EMPOWER);
				}
				for (String string : list) {
					temp.addAll(Arrays.asList(string.split(",")));
				}

				List<String> list2 = sysMenuDao.findPowerByMenuIds(temp);
				accessToken.setPermissions(list2.toString());
				accessToken.setSysUserName(user.getUserName());
				accessToken.setImageUrl(user.getImageUrl());
				return ResultData.success(accessToken);
			}
		}
	}

	/**
	 * 分页查询系统用户
	 */
	@Override
	public ResultData queryUserByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<SysUser> list = sysUserDao.queryUserByPage(name);
		if (list != null && !list.isEmpty()) {
			for (SysUser sysUser : list) {
				if (sysUser.getRoleIds() != null && !sysUser.getRoleIds().isEmpty()) {
					String[] roleIds = sysUser.getRoleIds().split(",");
					List<String> names = sysRoleDao.findRoleNameByRoleIds(roleIds);
					sysUser.setRoleName(names.toString());
				}
			}
		}
		PageInfo<SysUser> page = new PageInfo<SysUser>(list);
		return ResultData.success(page);
	}

	/**
	 * 给用户分配角色
	 */
	@Override
	public ResultData assignRole(SysUser user) {
		if (user.getRoleIds().isEmpty() || user.getRoleIds() == null) {
			return ResultData.warn(ResultCode.PARAMETER_ERROR, "请至少为其分配一个角色");
		}
		return ResultData.success(sysUserDao.assignRole(user));
	}

	/**
	 * 新增系统用户
	 */
	@Override
	public ResultData insertUser(SysUser user) {
		user.setPassWord(EncryptUtils.md5Encode(user.getPassWord()));
		// 根据部门id查询部门名称
//		String deptName = sysDeptDao.findDeptNameByid(user.getDeptid());
//		if (deptName.equals("中青汇智")) {
//			user.setType("1");
//			user.setRoleIds("2");
//		} else {
//			user.setType("2");
//			user.setRoleIds("3");
//		}
		try {
			return ResultData.success(sysUserDao.insertUser(user));
		} catch (Exception e) {
			return ResultData.warn(ResultCode.REGISTER_ERROR, "该账号已存在!");
		}
	}

	/**
	 * 新增机构普通成员
	 */
	@Override
	public ResultData insertJigouLeaguer(SysUser user, String userId) {
		user.setPassWord(EncryptUtils.md5Encode(user.getPassWord()));
		// 根据部门id查询部门名称
		user.setType("3");
		user.setRoleIds("4");
		user.setDeptid(sysUserDao.findSysUserByid(userId).getDeptid());
		try {
			return ResultData.success(sysUserDao.insertUser(user));
		} catch (Exception e) {
			return ResultData.warn(ResultCode.REGISTER_ERROR, "该前端用户已成为系统用户,或该账号已存在!");
		}
	}
}
