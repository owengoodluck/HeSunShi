package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CustomSpecification;

public interface CustomSpecificationDao {

	public List<CustomSpecification> queryByPage(@Param("type") Integer type);

	public int quotedPrice(CustomSpecification customSpecification);
	
	public CustomSpecification queryByCustomNumber(@Param("customNumber") String customNumber);

	public int isUpload(String customNumber);

}
