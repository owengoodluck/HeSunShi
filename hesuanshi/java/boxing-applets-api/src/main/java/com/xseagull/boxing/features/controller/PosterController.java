package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IPosterService;

@RestController
@RequestMapping("/poster")
public class PosterController {
	@Autowired
	private IPosterService posterService;

	/**
	 * 查询海报
	 */
	@GetMapping("/queryList")
	public ResultData queryList() {
		return posterService.queryList();
	}
}
