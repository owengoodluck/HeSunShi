package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;

/**
 * 用户退款记录VO
 * 
 * @author Administrator
 *
 */
public class RefundVO {

	private String specificationName;// 方案名称
	private String typeName;// 盒型
	private String materialName;// 材质
	private String layoutImage;// 版面图片
	private double length;
	private double width;
	private double height;
	
	private String orderNumber;// 订单号
	private String businessNumber;// 订单号
	private Integer status;// 退款状态
	private BigDecimal money;// 退款金额
	private Integer quantity;// 数量

	public String getLayoutImage() {
		return layoutImage;
	}

	public void setLayoutImage(String layoutImage) {
		this.layoutImage = layoutImage;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
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

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

}
