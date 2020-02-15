package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.features.entity.BoxTypeClassify;
import com.xseagull.boxing.features.entity.BoxTypeDetail;

/**
 * 包装盒型
 */
public interface BoxTypeDao {
	/**
	 * 查询分类
	 */
	public List<BoxTypeClassify> queryClassify();
	
	/**
	 * 查询前5个分类
	 */
	public List<BoxTypeClassify> queryClassify5();
	
	/**
	 * 查询分类详情
	 */
	public BoxTypeDetail queryClassifyDetailById(long id);

	/**
	 * 查询所有分类详情
	 */
	public List<BoxTypeClassify> queryClassifyDetailAll();
}
