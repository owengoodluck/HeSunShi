package com.xseagull.boxing.features.vo;

public class OrderVO {

	private long id;// id
	private String userId;// '用户id',
	private String nickName;// '用户名',
	private String phone;// '用户手机',
	private String orderNumber;// '订单编号',
	private String businessNumber;// '交易编号',
	private String contactName;// '买家姓名',
	private String contactPhone;// '买家手机号',
	private String contactAddress;// '买家地址',
	private String remark;// '买家留言',
	private int status;// '状态(0:待付款;1:待发货;2:待收货;3:交易成功;4:交易取消;5:交易删除;)',
	private int fareType;// '运费结算方式(0:寄方付;1:买家付;)',
	private String updateTime;// '更新时间',

	private String expressName;// 快递名称',
	private String serial;// '快递单号',

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public int getFareType() {
		return fareType;
	}

	public void setFareType(int fareType) {
		this.fareType = fareType;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
