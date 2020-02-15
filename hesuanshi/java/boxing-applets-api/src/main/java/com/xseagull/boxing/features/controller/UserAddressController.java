package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IUserAddressService;
import com.xseagull.boxing.features.vo.UserAddressVO;

@RestController
@RequestMapping("/userAddress")
public class UserAddressController {

	@Autowired
	private IUserAddressService userAddressServiceImpl;

	/**
	 * 查询用户收货地址列表
	 */
	@GetMapping("/findAddressList")
	public ResultData findAddressList(long userId) {
		return userAddressServiceImpl.findAddressList(userId);
	}

	/**
	 * 设为默认地址
	 */
	@GetMapping("/setMoren/{id}")
	public ResultData setMoren(@PathVariable long id, long userId) {
		return userAddressServiceImpl.setMoren(id, userId);
	}
	
	/**
	 * 删除地址
	 */
	@GetMapping("/delAddress/{id}")
	public ResultData delAddress(@PathVariable long id) {
		return userAddressServiceImpl.delAddress(id);
	}
	
	/**
	 * 保存地址
	 */
	@PostMapping("/saveAddress")
	public ResultData saveAddress(UserAddressVO userAddressVO) {
		return userAddressServiceImpl.saveAddress(userAddressVO);
	}
	
	/**
	 * 查询用户默认收货地址
	 */
	@GetMapping("/findMorenAddress")
	public ResultData findMorenAddress(long userId) {
		return userAddressServiceImpl.findMorenAddress(userId);
	}
}
