package com.xseagull.boxing.features.entity;

public class SecondPay {
	private long orderId;
	private long userId;// 用户id',
	private String orderNumber;// 订单编号',
	private String businessNumber;// 交易编号',
	private double countPrice;// 金额

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public double getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(double countPrice) {
		this.countPrice = countPrice;
	}

	@Override
	public String toString() {
		return "SecondPay [orderId=" + orderId + ", userId=" + userId + ", orderNumber=" + orderNumber
				+ ", businessNumber=" + businessNumber + ", countPrice=" + countPrice + "]";
	}

}
