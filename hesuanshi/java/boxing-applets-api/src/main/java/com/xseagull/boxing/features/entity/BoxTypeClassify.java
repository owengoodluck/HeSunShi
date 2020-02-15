package com.xseagull.boxing.features.entity;

import java.util.List;

/**
 *  盒型分类
 */
public class BoxTypeClassify {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  包装盒型列表
	 */
	private List<BoxTypeDetail> boxTypeDetailList;
	
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
	public List<BoxTypeDetail> getBoxTypeDetailList() {
		return boxTypeDetailList;
	}
	public void setBoxTypeDetailList(List<BoxTypeDetail> boxTypeDetailList) {
		this.boxTypeDetailList = boxTypeDetailList;
	}
}
