package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	 * 保存方案
	 */
	@PostMapping("/insert")
	public ResultData insert(CustomSpecification customSpecification) {
		return customSpecificationService.insert(customSpecification);
	}

	/**
	 * 修改方案
	 */
	@PostMapping("/update")
	public ResultData update(CustomSpecification customSpecification) {
		return customSpecificationService.update(customSpecification);
	}

	/**
	 * 方案分页
	 */
	@PostMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "type", defaultValue = "0") Integer type, long userId) {
		return customSpecificationService.queryByPage(pageNo, pageSize, type, userId);
	}

	/**
	 * 删除方案
	 */
	@GetMapping("delete/{id}")
	public ResultData delete(@PathVariable long id) {
		return customSpecificationService.delete(id);
	}
}
