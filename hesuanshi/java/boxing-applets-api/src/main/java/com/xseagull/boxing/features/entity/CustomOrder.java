package com.xseagull.boxing.features.entity;

import com.xseagull.boxing.constant.OrderStatusEnum;

public class CustomOrder {
	private Long id;// 主键',
	private Long userId;// 用户id',
	private String orderNumber;// 订单编号',
	private String businessNumber;// 交易编号',
	private String contactName;// 买家姓名',
	private String contactPhone;// 买家手机号',
	private String contactAddress;// 买家地址',
	private String status;//状态(0:待付款（定金）;90:工厂打样ing;91:等待客户样品确认;1:待发货;2:待收货;3:待评价;4:交易成功;5:已取消;6:已删除;7:退款中)'
	private String remark;// 买家留言,
	private String formId;// 通知id,
	private int fareType;// 运费支付方式(0: 寄方付;1: 买方付),
	private String paymentType;//1:全款; 2:定金; 3:尾款
	private String customList;// 购物车里传过来的方案集合字符串

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public int getFareType() {
		return fareType;
	}

	public void setFareType(int fareType) {
		this.fareType = fareType;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCustomList() {
		return customList;
	}

	public void setCustomList(String customList) {
		this.customList = customList;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	@Override
	public String toString() {
		return "CustomOrder [id=" + id + ", userId=" + userId + ", orderNumber=" + orderNumber + ", businessNumber="
				+ businessNumber + ", contactName=" + contactName + ", contactPhone=" + contactPhone
				+ ", contactAddress=" + contactAddress + ", status=" + status + ", remark=" + remark + ", customList="
				+ customList + "]";
	}

}
