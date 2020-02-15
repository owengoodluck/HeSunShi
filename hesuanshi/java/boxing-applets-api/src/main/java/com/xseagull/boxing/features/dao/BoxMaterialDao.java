package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxMaterialClassify;
import com.xseagull.boxing.features.entity.BoxMaterialDetail;

/**
 * 包装材质
 */
public interface BoxMaterialDao {
	/**
	 * 查询分类
	 */
	public List<BoxMaterialClassify> queryClassify(@Param("boxTypeDetailId") long boxTypeDetailId);
	/**
	 * 查询分类详情
	 */
	public BoxMaterialDetail queryClassifyDetailById(long id);
	/**
	 * 查询总数
	 * @return
	 */
	public int findClassifyDetailNumByTId(long tId);
	
	/**
	 * 根据盒型id查所有详情
	 * @param tId
	 * @return
	 */
	public List<BoxMaterialDetail> queryClassifyDetailByTId(long tId);
}
