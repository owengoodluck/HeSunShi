package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.features.entity.Poster;

public interface PosterDao {
	/**
	 * 查询海报列表
	 */
	public List<Poster> queryList();
	/**
	 * 查询海报详情
	 */
	public Poster queryDetailById(long id);
}
