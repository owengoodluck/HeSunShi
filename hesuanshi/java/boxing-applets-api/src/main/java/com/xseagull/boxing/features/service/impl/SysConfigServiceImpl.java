package com.xseagull.boxing.features.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.SysConfigDao;
import com.xseagull.boxing.features.entity.SysConfig;
import com.xseagull.boxing.features.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysConfigServiceImpl implements ISysConfigService {

	@Autowired
	private SysConfigDao sysConfigDao;


	// 查询配置选项
	@Override
	public ResultData findConfig() {
		List<SysConfig> list = sysConfigDao.findConfig();
		return ResultData.success(list);
	}

	// 新增配置选项
	@Override
	public ResultData insertConfig(SysConfig config) {
		return ResultData.success(sysConfigDao.insertConfig(config));
	}

	// 分页查询配置选项信息
	@Override
	public ResultData queryConfigByPage(Integer pageNo, Integer pageSize, String itemKey) {
		PageHelper.startPage(pageNo, pageSize);
		List<SysConfig> list = sysConfigDao.queryConfigByPage(itemKey);
		PageInfo<SysConfig> page = new PageInfo<SysConfig>(list);
		return ResultData.success(page);
	}

	@Override
	public ResultData queryConfigByItemKey(String itemKey) {
		return ResultData.success(sysConfigDao.queryConfigByItemKey(itemKey));
	}

	// 更新配置选项
	@Override
	public ResultData updateConfig(SysConfig config) {
		return ResultData.success(sysConfigDao.updateConfig(config));
	}

	// 删除配置选项
	@Override
	public ResultData deleteConfig(String id) {
		return ResultData.success(sysConfigDao.deleteConfig(id));
	}
}
