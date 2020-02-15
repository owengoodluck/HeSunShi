package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxLayoutClassify;

public interface BoxLayoutClassifyDao {

	// 新增版面分类
	public int insert(BoxLayoutClassify boxLayoutClassify);

	// 分页版面分类
	List<BoxLayoutClassify> queryByPage(@Param("name") String name);

	// 更新版面分类
	public int update(BoxLayoutClassify boxLayoutClassify);

	// 查询版面用于回显下拉框
	List<BoxLayoutClassify> findClassify();

}
