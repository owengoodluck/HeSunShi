package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;

public class OrderDetailVO {

	private long id;// '主键',
	private long orderId;// '订单id',
	private String type;// '类型(1:定制;2:现货;)',
	private BigDecimal price;// '价格',
	private BigDecimal fare;// '运费',
	private String status;// '状态(0:待完成;1:已完成;2:退款中;3:已退款)',
	private int quantity;// '数量',

	private String specificationName;
	private String typeName;
	private String typeImage;
	private String materialName;
	private String materialImage;
	private String layoutName;
	private String layoutImage;
	private String printMethodName;
	private double length;
	private double width;
	private double height;

	public String getPrintMethodName() {
		return printMethodName;
	}

	public void setPrintMethodName(String printMethodName) {
		this.printMethodName = printMethodName;
	}

	public String getTypeImage() {
		return typeImage;
	}

	public void setTypeImage(String typeImage) {
		this.typeImage = typeImage;
	}

	public String getMaterialImage() {
		return materialImage;
	}

	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
	}

	public BigDecimal getFare() {
		return fare;
	}

	public void setFare(BigDecimal fare) {
		this.fare = fare;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getLayoutName() {
		return layoutName;
	}

	public void setLayoutName(String layoutName) {
		this.layoutName = layoutName;
	}

	public String getLayoutImage() {
		return layoutImage;
	}

	public void setLayoutImage(String layoutImage) {
		this.layoutImage = layoutImage;
	}

	public double getLength() {
		return length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [id=" + id + ", orderId=" + orderId + ", type=" + type + ", price=" + price + ", fare="
				+ fare + ", status=" + status + ", quantity=" + quantity + ", specificationName=" + specificationName
				+ ", typeName=" + typeName + ", materialName=" + materialName + ", layoutName=" + layoutName
				+ ", layoutImage=" + layoutImage + ", length=" + length + ", width=" + width + ", height=" + height
				+ "]";
	}

}
