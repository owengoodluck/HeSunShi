package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxLayout;

public interface BoxLayoutDao {

	// 新增版面
	public int insert(BoxLayout boxLayout);

	// 分页版面
	List<BoxLayout> queryByPage(@Param("name") String name);

	// 更新版面
	public int update(BoxLayout boxLayout);

	// 删除版面
	public int delete(@Param("id") long id);

	// 根据盒型id查询版面数量
	public int findNumByBoxtypeId(@Param("typeId") long typeId);

}
