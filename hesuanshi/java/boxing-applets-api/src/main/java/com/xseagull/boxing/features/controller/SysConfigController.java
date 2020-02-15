package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.ISysConfigService;

@RestController
@RequestMapping("/sysConfig")
public class SysConfigController {
	@Autowired
	private ISysConfigService sysConfigServiceImpl;
	/**
	 * 根据建值找配置信息
	 */
	@PostMapping("/findByKey")
	public ResultData findByKey(String key) {
		return sysConfigServiceImpl.queryConfigByItemKey(key);
	}
}
