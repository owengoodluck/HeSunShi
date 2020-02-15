package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class BoxMaterial {

	private long id;
	private long classifyId;// 材质分类id
	private String classifyName;// 材质分类名称
	private long boxTypeId;// 包装盒型id
	private String boxTypeName;// 包装盒型名称
	private String name;
	private String image;
	private BigDecimal price;// 价格
	private BigDecimal grams;// 克数
	private String remark;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public long getBoxTypeId() {
		return boxTypeId;
	}

	public void setBoxTypeId(long boxTypeId) {
		this.boxTypeId = boxTypeId;
	}

	public String getBoxTypeName() {
		return boxTypeName;
	}

	public void setBoxTypeName(String boxTypeName) {
		this.boxTypeName = boxTypeName;
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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getGrams() {
		return grams;
	}

	public void setGrams(BigDecimal grams) {
		this.grams = grams;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BoxMaterial [id=" + id + ", classifyId=" + classifyId + ", classifyName=" + classifyName
				+ ", boxTypeId=" + boxTypeId + ", boxTypeName=" + boxTypeName + ", name=" + name + ", image=" + image
				+ ", price=" + price + ", grams=" + grams + ", remark=" + remark + ", status=" + status + "]";
	}

}
