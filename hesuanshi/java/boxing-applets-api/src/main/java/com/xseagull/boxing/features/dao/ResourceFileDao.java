package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.ResourceFile;

public interface ResourceFileDao {
	public int insert(ResourceFile resourceFile);

	public void delete(ResourceFile resourceFile);

	// 根据编号查询资源列表
	public List<ResourceFile> findResourceByNumber(@Param("number") String number);
	
	/**
	 * 
	 * @param type
	 * @param number
	 * @return
	 */
	public ResourceFile findResourceByTypeAndNumber(@Param("type") String type, @Param("number") String number);
	
	
}
