package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IPhoneCodeService;

@RestController
@RequestMapping("/phoneCode")
public class PhoneCodeController {
	@Autowired
	private IPhoneCodeService phoneCodeService;
	
	
	/**
	 * 根据手机号查询最后一条验证码
	 * @param id
	 * @return
	 */
	@GetMapping("/findLastCode/{phone}")
	public ResultData findLastCode(@PathVariable String phone) {
		return phoneCodeService.findLastCode(phone);
	}
}
