package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.BoxLayoutClassify;
import com.xseagull.boxing.features.entity.BoxLayoutDetail;

/**
 * 包装版面
 */
public interface BoxLayoutDao {
	/**
	 * 查询分类
	 */
	public List<BoxLayoutClassify> queryClassify(@Param("boxTypeDetailId") long boxTypeDetailId);
	/**
	 * 查询分类详情
	 */
	public BoxLayoutDetail queryClassifyDetailById(long id);
	
	/**
	 * 查询分类详情
	 */
	public List<BoxLayoutDetail> queryClassifyDetailByTId(long tId);
}
