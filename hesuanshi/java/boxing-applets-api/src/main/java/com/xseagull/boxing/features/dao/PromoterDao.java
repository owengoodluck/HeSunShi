package com.xseagull.boxing.features.dao;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.Promoter;

/**
 * 推广人员管理
 */
public interface PromoterDao {

	Promoter findPromoterByUserId(@Param("userId") long userId);

	public void update(Promoter promoter);

	public void insert(Promoter promoter);

	public int addNumber(@Param("userId") long userId);

}
