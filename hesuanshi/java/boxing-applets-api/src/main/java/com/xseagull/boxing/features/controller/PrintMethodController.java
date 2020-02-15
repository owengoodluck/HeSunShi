package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IPrintMethodService;

@RestController
@RequestMapping("/printMethod")
public class PrintMethodController {
	@Autowired
	private IPrintMethodService printMethodService;

	/**
	 * 查询列表
	 */
	@GetMapping("/queryList")
	public ResultData queryList() {
		return printMethodService.queryList();
	}
}
