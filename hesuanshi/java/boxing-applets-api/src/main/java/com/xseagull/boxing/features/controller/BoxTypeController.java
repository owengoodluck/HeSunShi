package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Calculated;
import com.xseagull.boxing.features.service.IBoxTypeService;

@RestController
@RequestMapping("/boxType")
public class BoxTypeController {
	@Autowired
	private IBoxTypeService boxTypeService;

	/**
	 * 查询分类
	 */
	@GetMapping("/queryClassify")
	public ResultData queryClassify() {
		return boxTypeService.queryClassify();
	}
	
	/**
	 * 查询前5个分类
	 */
	@GetMapping("/queryClassify5")
	public ResultData queryClassify5() {
		return boxTypeService.queryClassify5();
	}
	
	@GetMapping("/queryClassifyDetailAll")
	public ResultData queryClassifyDetailAll() {
		return boxTypeService.queryClassifyDetailAll();
	}
	
	/**
	 * 查询分类详情
	 */
	@GetMapping("/queryClassifyDetailById")
	public ResultData queryClassifyDetailById(long id) {
		return boxTypeService.queryClassifyDetailById(id);
	}
	
	/**
	 * 计算金额
	 */
	@PostMapping("/calculated")
	public ResultData calculated(Calculated calculated) {
		return boxTypeService.calculated(calculated);
	}

	/**
	 * 定金金额
	 */
	@PostMapping("/getAdvancePaymentPrice")
	public ResultData getAdvancePaymentPrice() {
		return boxTypeService.getAdvancePaymentPrice();
	}
}
