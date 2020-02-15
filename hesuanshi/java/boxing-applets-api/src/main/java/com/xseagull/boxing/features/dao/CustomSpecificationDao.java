package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CustomSpecification;

public interface CustomSpecificationDao {
	public int insert(CustomSpecification customSpecification);

	public int update(CustomSpecification customSpecification);

	public List<CustomSpecification> queryByPage(@Param("type") Integer type, @Param("userId") long userId);

	public int updateStatus(@Param("id") long id, @Param("status") Integer status);
}
