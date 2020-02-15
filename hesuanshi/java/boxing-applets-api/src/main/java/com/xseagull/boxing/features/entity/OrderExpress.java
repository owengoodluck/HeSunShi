package com.xseagull.boxing.features.entity;

public class OrderExpress {

	private long id;// '主键',
	private long orderId;// '订单id',
	private long expressId;// 快递id',
	private String serial;// '快递编号'

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

	@Override
	public String toString() {
		return "OrderExpress [id=" + id + ", orderId=" + orderId + ", expressId=" + expressId + ", serial=" + serial
				+ "]";
	}

}
