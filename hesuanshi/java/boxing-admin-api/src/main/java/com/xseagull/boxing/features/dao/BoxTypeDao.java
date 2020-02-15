package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxType;

public interface BoxTypeDao {

	// 新增盒型
	public int insert(BoxType boxType);

	// 分页盒型
	List<BoxType> queryByPage(@Param("name") String name);

	// 更新盒型
	public int update(BoxType boxType);

	// 删除盒型
	public int delete(@Param("id") long id);

	// 查询盒型用于下拉框回显
	List<BoxType> findBoxType();

}
