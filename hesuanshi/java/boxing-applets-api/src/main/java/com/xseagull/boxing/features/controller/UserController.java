package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.entity.UserCompany;
import com.xseagull.boxing.features.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	/**
	 * 根据用户id查询用户信息
	 */
	@GetMapping("/getUserDetail")
	public ResultData getUserDetail(String userId) {
		return userService.getUserDetail(userId);
	}

	/**
	 * 更新用户信息
	 */
	@PostMapping("/update")
	public ResultData update(User user, String userId) {
		return userService.update(user, userId);
	}

	/**
	 * 绑定手机号
	 */
	@PostMapping("/updatPhone")
	public ResultData updatPhone(String userId, String phone, String code) {
		return userService.updatPhone(userId, phone, code);
	}

	/**
	 * 根据用户id查询用户企业信息
	 */
	@GetMapping("/getUserCompanyDetail")
	public ResultData getUserCompanyDetail(long userId) {
		return userService.getUserCompanyDetail(userId);
	}
	
	/**
	 * 保存用户企业信息
	 */
	@PostMapping("/saveUserCompany")
	public ResultData saveUserCompany(UserCompany userCompany) {
		return userService.saveUserCompany(userCompany);
	}
	
	/**
	 * 我邀请的用户列表
	 */
	@PostMapping("/findMyUser")
	public ResultData findMyUser(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return userService.findMyUser(pageNo, pageSize, userId);
	}
}
