package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Poster;

/**
 * 推广海报
 */
public interface PosterDao {

	List<Poster> queryByPage();

	public int insert(Poster poster);

	public int delete(@Param("id") String id);

}
