package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxMaterialClassify;

public interface BoxMaterialClassifyDao {

	// 新增材质分类
	public int insert(BoxMaterialClassify boxMaterialClassify);

	// 分页材质分类
	List<BoxMaterialClassify> queryByPage(@Param("name") String name);

	// 更新材质分类
	public int update(BoxMaterialClassify boxMaterialClassify);

	// 查询材质用于回显下拉框
	List<BoxMaterialClassify> findClassify();

}
