package com.xseagull.boxing.pay;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class OrderPay {
	/**
	 * 用户id
	 */
	private long userId;
	/**
	 * 支付类型(1:支付宝;2:微信;)
	 */
	@Min(value = 1, message = "支付类型不合法")
	@Max(value = 2, message = "支付类型不合法")
	private int payType;
	/**
	 * 商品名称
	 */
	private String subject = "充值";

	/**
	 * 商品描述
	 */
	private String body = "充值";

	/**
	 * 订单总金额，单位为元
	 */
	@DecimalMin(value = "0.01", message = "金额最小值不能小于0.01")
	@Digits(integer = 10, fraction = 2, message = "金额值必须在可接受的范围内")
	private double totalAmount;
	/**
	 * 商户系统内部的订单号
	 */
	private String outTradeNo;

	/**
	 * 通知地址
	 */
	private String notifyUrl;

	private String openid;

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getPayType() {
		return payType;
	}

	public void setPayType(int payType) {
		this.payType = payType;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getOutTradeNo() {
		return outTradeNo;
	}

	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

}
