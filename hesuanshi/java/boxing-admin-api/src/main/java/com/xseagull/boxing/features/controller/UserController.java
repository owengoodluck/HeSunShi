package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private IUserService userService;

	/**
	 * 分页查询用户信息
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param nickName
	 * @return
	 */
	@GetMapping("/queryUserByPage")
	public ResultData queryUserByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String nickName) {
		return userService.queryUserByPage(pageNo, pageSize, nickName);
	}

	/**
	 * 根据手机号查询用户
	 * @param phone
	 * @return
	 */
	@GetMapping("/findUserByPhone/{phone}")
	public ResultData findUserByPhone(@PathVariable String phone) {
		return userService.findUserByPhone(phone);
	}
	
	/**
	 * 根据用户id查询邀请的用户列表
	 */
	@PostMapping("/findUserByRecommenderId")
	public ResultData findUserByRecommenderId(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long uId) {
		return userService.findUserByRecommenderId(pageNo, pageSize, uId);
	}
	
}
