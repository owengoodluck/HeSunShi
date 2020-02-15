package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysUser;
import com.xseagull.boxing.features.service.ISysUserService;

@RestController
@RequestMapping("/sysUser")
public class SysUserController {
	@Autowired
	private ISysUserService sysUserService;
	
	/**
	 * 系统用户登录
	 * @param sysUser
	 * @return
	 */
	@PostMapping("/login")
	public ResultData login(SysUser sysUser) {
		return sysUserService.queryUser(sysUser);
	}

	/**
	 * 分页查询系统用户
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param name
	 *            搜索条件(用户名称)
	 * @return
	 */
	@GetMapping("/queryUserByPage")
	public ResultData queryUserByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "name") String name) {
		return sysUserService.queryUserByPage(pageNo, pageSize, name);
	}

	/**
	 * 给用户分配角色
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/assignRole")
	public ResultData assignRole(SysUser user) {
		return sysUserService.assignRole(user);
	}
	
	/**
	 * 新增系统用户
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/insertUser")
	public ResultData insertUser(SysUser user) {
		return sysUserService.insertUser(user);
	}
	
	/**
	 * 新增机构普通成员
	 * 
	 * @param user
	 * @return
	 */
	@PostMapping("/insertJigouLeaguer")
	public ResultData insertJigouLeaguer(SysUser user,String userId) {
		return sysUserService.insertJigouLeaguer(user,userId);
	}
}
