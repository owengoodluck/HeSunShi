package com.xseagull.boxing.features.vo;

public class OrderDetailVO {

	/**
	 *  主键
	 */
	private long id;
	private long uId;
	private String businessNumber;// 交易编号
	private String orderNumber;// 订单编号
	private int fareType;// 运费支付方式(0: 寄方付;1: 买方付)
	/**
	 *  件数
	 */
	private int quantity;
	/**
	 *  价格
	 */
	private double price;
	/**
	 *  运费
	 */
	private double fare;
	/**
	 * 状态
	 */
	private int status;
	/**
	 *  方案名称
	 */
	private String specificationName;

	/**
	 *  盒型名称
	 */
	private String typeName;
	/**
	 *  盒型图片
	 */
	private String typeImage;

	/**
	 *  材质名称
	 */
	private String materialName;
	/**
	 *  材质图片
	 */
	private String materialImage;

	/**
	 *  版面名称
	 */
	private String layoutName;
	/**
	 *  版面图片
	 */
	private String layoutImage;

	/**
	 *  印刷方式名称
	 */
	private String printMethodName;
	/**
	 *  长
	 */
	private double length;
	/**
	 *  宽
	 */
	private double width;
	/**
	 *  高
	 */
	private double height;
	private String formId;

	public int getFareType() {
		return fareType;
	}

	public void setFareType(int fareType) {
		this.fareType = fareType;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	private String updateTime;

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getFare() {
		return fare;
	}

	public void setFare(double fare) {
		this.fare = fare;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public String getTypeImage() {
		return typeImage;
	}

	public void setTypeImage(String typeImage) {
		this.typeImage = typeImage;
	}

	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getMaterialImage() {
		return materialImage;
	}

	public void setMaterialImage(String materialImage) {
		this.materialImage = materialImage;
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

	public String getPrintMethodName() {
		return printMethodName;
	}

	public void setPrintMethodName(String printMethodName) {
		this.printMethodName = printMethodName;
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

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public String toString() {
		return "OrderDetailVO [id=" + id + ", uId=" + uId + ", businessNumber=" + businessNumber + ", orderNumber="
				+ orderNumber + ", quantity=" + quantity + ", price=" + price + ", fare=" + fare + ", status=" + status
				+ ", specificationName=" + specificationName + ", typeName=" + typeName + ", typeImage=" + typeImage
				+ ", materialName=" + materialName + ", materialImage=" + materialImage + ", layoutName=" + layoutName
				+ ", layoutImage=" + layoutImage + ", printMethodName=" + printMethodName + ", length=" + length
				+ ", width=" + width + ", height=" + height + ", formId=" + formId + ", updateTime=" + updateTime + "]";
	}

}
