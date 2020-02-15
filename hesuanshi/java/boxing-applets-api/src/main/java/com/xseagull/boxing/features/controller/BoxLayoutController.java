package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IBoxLayoutService;

@RestController
@RequestMapping("/boxLayout")
public class BoxLayoutController {
	@Autowired
	private IBoxLayoutService boxLayoutService;

	/**
	 * 查询分类
	 */
	@GetMapping("/queryClassify")
	public ResultData queryClassify(long boxTypeDetailId) {
		return boxLayoutService.queryClassify(boxTypeDetailId);
	}
	
	/**
	 * 查询分类详情
	 */
	@GetMapping("/queryClassifyDetailById")
	public ResultData queryClassifyDetailById(long id) {
		return boxLayoutService.queryClassifyDetailById(id);
	}
	
	/**
	 * 查询分类详情
	 */
	@GetMapping("/queryClassifyDetailByTId")
	public ResultData queryClassifyDetailByTId(long tId) {
		return boxLayoutService.queryClassifyDetailByTId(tId);
	}
	
}
