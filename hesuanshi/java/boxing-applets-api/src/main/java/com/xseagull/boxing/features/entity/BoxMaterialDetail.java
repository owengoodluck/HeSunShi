package com.xseagull.boxing.features.entity;

/**
 *  包装材质
 */
public class BoxMaterialDetail {
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
	/**
	 *  价格
	 */
	private String price;
	/**
	 *  克数
	 */
	private String grams;

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
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getGrams() {
		return grams;
	}
	public void setGrams(String grams) {
		this.grams = grams;
	}
}
