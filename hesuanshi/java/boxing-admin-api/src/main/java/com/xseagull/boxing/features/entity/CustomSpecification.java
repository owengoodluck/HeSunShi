package com.xseagull.boxing.features.entity;

/**
 *  定制规格
 */
public class CustomSpecification {
	/**
	 *  主键
	 */
	private long id;
	/**
	 *  用户id
	 */
	private long uId;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 *  类型(1:已报价;2:未报价;)
	 */
	private String type;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  编号
	 */
	private String customNumber;
	/**
	 *  盒型id
	 */
	private long typeId;
	/**
	 *  盒型名称
	 */
	private String typeName;
	/**
	 *  盒型图片
	 */
	private String typeImage;
	/**
	 *  材质id
	 */
	private long materialId;
	/**
	 *  材质名称
	 */
	private String materialName;
	/**
	 *  材质图片
	 */
	private String materialImage;
	/**
	 *  版面id
	 */
	private long layoutId;
	/**
	 *  版面名称
	 */
	private String layoutName;
	/**
	 *  版面图片
	 */
	private String layoutImage;
	/**
	 *  印刷方式id
	 */
	private long printMethodId;
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
	/**
	 *  件数
	 */
	private int piece;
	/**
	 *  价格
	 */
	private double price;
	/**
	 *  运费
	 */
	private double fare;
	/**
	 *  formid
	 */
	private String formId;
	/**
	 * 更新时间
	 */
	private String updateTime;
	/**
	 * 是否上传
	 * 0：未上传（默认）
	 * 1：已上传
	 */
	private String isUpload;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCustomNumber() {
		return customNumber;
	}

	public void setCustomNumber(String customNumber) {
		this.customNumber = customNumber;
	}

	public long getTypeId() {
		return typeId;
	}

	public void setTypeId(long typeId) {
		this.typeId = typeId;
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

	public long getMaterialId() {
		return materialId;
	}

	public void setMaterialId(long materialId) {
		this.materialId = materialId;
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

	public long getLayoutId() {
		return layoutId;
	}

	public void setLayoutId(long layoutId) {
		this.layoutId = layoutId;
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

	public long getPrintMethodId() {
		return printMethodId;
	}

	public void setPrintMethodId(long printMethodId) {
		this.printMethodId = printMethodId;
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

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
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

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
	}

	@Override
	public String toString() {
		return "CustomSpecification [id=" + id + ", uId=" + uId + ", nickName=" + nickName + ", phone=" + phone
				+ ", type=" + type + ", name=" + name + ", customNumber=" + customNumber + ", typeId=" + typeId
				+ ", typeName=" + typeName + ", typeImage=" + typeImage + ", materialId=" + materialId
				+ ", materialName=" + materialName + ", materialImage=" + materialImage + ", layoutId=" + layoutId
				+ ", layoutName=" + layoutName + ", layoutImage=" + layoutImage + ", printMethodId=" + printMethodId
				+ ", printMethodName=" + printMethodName + ", length=" + length + ", width=" + width + ", height="
				+ height + ", piece=" + piece + ", price=" + price + ", fare=" + fare + ", formId=" + formId
				+ ", updateTime=" + updateTime + ", isUpload=" + isUpload + "]";
	}

	public String getIsUpload() {
		return isUpload;
	}

	public void setIsUpload(String isUpload) {
		this.isUpload = isUpload;
	}

}
