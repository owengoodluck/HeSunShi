package com.xseagull.boxing.features.entity;

import java.util.List;

/**
 *  材质分类
 */
public class BoxMaterialClassify {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  包装材质列表
	 */
	private List<BoxMaterialDetail> boxMaterialDetailList;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<BoxMaterialDetail> getBoxMaterialDetailList() {
		return boxMaterialDetailList;
	}
	public void setBoxMaterialDetailList(List<BoxMaterialDetail> boxMaterialDetailList) {
		this.boxMaterialDetailList = boxMaterialDetailList;
	}
}
