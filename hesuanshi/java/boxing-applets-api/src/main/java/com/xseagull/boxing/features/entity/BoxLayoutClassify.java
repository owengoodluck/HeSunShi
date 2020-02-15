package com.xseagull.boxing.features.entity;

import java.util.List;

/**
 *  版面分类
 */
public class BoxLayoutClassify {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  包装版面列表
	 */
	private List<BoxLayoutDetail> boxLayoutDetailList;
	
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
	public List<BoxLayoutDetail> getBoxLayoutDetailList() {
		return boxLayoutDetailList;
	}
	public void setBoxLayoutDetailList(List<BoxLayoutDetail> boxLayoutDetailList) {
		this.boxLayoutDetailList = boxLayoutDetailList;
	}
}
