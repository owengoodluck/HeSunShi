package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.SysConfig;

public interface ISysConfigService {

	// 查询配置选项用于回显下拉框
	ResultData findConfig();

	// 新增配置选项
	ResultData insertConfig(SysConfig config);

	// 分页查询配置选项信息
	ResultData queryConfigByPage(Integer pageNo, Integer pageSize, String itemKey);

	// 根据Config Key查询
	ResultData queryConfigByItemKey(String itemKey);

	// 更新配置选项
	ResultData updateConfig(SysConfig config);

	// 删除配置选项
	ResultData deleteConfig(String id);

}
