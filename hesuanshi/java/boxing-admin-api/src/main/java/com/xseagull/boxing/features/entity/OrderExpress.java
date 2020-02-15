package com.xseagull.boxing.features.entity;

public class OrderExpress {

	private long id;// '主键',
	private long uId;// '用户id',
	private long orderId;// '订单id',
	private long expressId;// 快递id',
	private String orderNumber;// '订单编号',
	private String serial;// '快递编号',
	private String createTime;// '创建时间',
	private String updateTime;// '更新时间',

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

	public long getExpressId() {
		return expressId;
	}

	public void setExpressId(long expressId) {
		this.expressId = expressId;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

}
