package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxTypeClassify;

public interface BoxTypeClassifyDao {

	// 新增盒型分类
	public int insert(BoxTypeClassify boxTypeClassify);

	// 分页盒型分类
	List<BoxTypeClassify> queryByPage(@Param("name") String name);

	// 更新盒型分类
	public int update(BoxTypeClassify boxTypeClassify);

	// 查询部门用于回显下拉框
	List<BoxTypeClassify> findClassify();

}
