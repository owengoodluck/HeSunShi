package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CustomSpecification;
import com.xseagull.boxing.features.service.ICustomSpecificationService;

@RestController
@RequestMapping("/customSpecification")
public class CustomSpecificationController {
	@Autowired
	private ICustomSpecificationService customSpecificationService;

	/**
	 * 方案分页
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "type", defaultValue = "0") Integer type) {
		return customSpecificationService.queryByPage(pageNo, pageSize, type);
	}
	
	/**
	 * 方案报价
	 */
	@PostMapping("/quotedPrice")
	public ResultData quotedPrice(CustomSpecification customSpecification) {
		return customSpecificationService.quotedPrice(customSpecification);
	}
	
	/**
	 * 根据方案编号查方案内容
	 * @param customNumber
	 * @return
	 */
	@PostMapping("/queryByCustomNumber")
	public ResultData queryByCustomNumber(@RequestParam(value = "customNumber") String  customNumber) {
		return customSpecificationService.queryByCustomNumber(customNumber);
	}
	
	
	/**
	 * 根据方案编号更新为已经
	 * @param customNumber
	 * @return
	 */
	@PostMapping("/isUpload")
	public ResultData isUpload(@RequestParam(value = "customNumber") String  customNumber) {
		return customSpecificationService.isUpload(customNumber);
	}
}
