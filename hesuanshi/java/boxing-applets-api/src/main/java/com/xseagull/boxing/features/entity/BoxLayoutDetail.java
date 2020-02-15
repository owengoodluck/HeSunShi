package com.xseagull.boxing.features.entity;

/**
 *  包装版面
 */
public class BoxLayoutDetail {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  图片
	 */
	private String image;
	/**
	 *  备注
	 */
	private String remark;

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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}
