package com.xseagull.boxing.features.dao;

import com.xseagull.boxing.features.entity.SysConfig;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysConfigDao {

	// 查询配置选项用于回显下拉框
	List<SysConfig> findConfig();

	// 新增配置选项
	public int insertConfig(SysConfig config);

	// 分页查询配置选项信息
	public List<SysConfig> queryConfigByPage(@Param("itemKey") String itemKey);

	// 根据itemKey查询配置选项信息
	public SysConfig queryConfigByItemKey(@Param("itemKey") String itemKey);

	// 更新配置选项
	public int updateConfig(SysConfig config);

	// 删除配置选项
	public int deleteConfig(String id);


}
