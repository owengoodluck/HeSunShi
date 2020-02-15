package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxMaterial;

public interface BoxMaterialDao {

	// 新增材质
	public int insert(BoxMaterial boxMaterial);

	// 分页材质
	List<BoxMaterial> queryByPage(@Param("name") String name);

	// 更新材质
	public int update(BoxMaterial boxMaterial);

	// 删除材质
	public int delete(@Param("id") long id);

	// 根据盒型id查询材质条数
	public int findNumByBoxtypeId(@Param("typeId") long typeId);

}
