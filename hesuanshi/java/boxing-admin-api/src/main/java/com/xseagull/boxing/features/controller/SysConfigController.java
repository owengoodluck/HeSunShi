package com.xseagull.boxing.features.controller;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysConfig;
import com.xseagull.boxing.features.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;


@RestController
@RequestMapping("/sys_config")
public class SysConfigController {

	@Autowired
	private ISysConfigService sysConfigServiceImpl;

	/**
	 * 查询配置选项
	 * 
	 * @return
	 */
	@GetMapping("/findConfig")
	public ResultData findConfig() {
		return sysConfigServiceImpl.findConfig();
	}

	/**
	 * 新增配置选项
	 * 
	 * @param config
	 * @return
	 */
	@PostMapping("/insertConfig")
	public ResultData insertConfig(SysConfig config) {
		return sysConfigServiceImpl.insertConfig(config);
	}

	/**
	 * 分页查询配置选项
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @param itemKey
	 * @return
	 */
	@GetMapping("/queryConfigByPage")
	public ResultData queryConfigByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "itemKey") String itemKey) {
		ResultData result = sysConfigServiceImpl.queryConfigByPage(pageNo, pageSize, itemKey);
		return result;
	}

	/**
	 * 更新配置选项
	 * 
	 * @param config
	 * @return
	 */
	@PostMapping("/updateConfig")
	public ResultData updateConfig(SysConfig config) {
		return sysConfigServiceImpl.updateConfig(config);
	}

	/**
	 * 删除配置选项
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/deleteConfig/{id}")
	public ResultData deleteConfig(@PathVariable String id) {
		return sysConfigServiceImpl.deleteConfig(id);
	}
}
