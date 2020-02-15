package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

/**
 * 退款信息
 */
public class Refund {
	private long id;// 主键id
	private long userId;// 用户id
	private long orderDetailId;// 订单详情id
	private String refundNumber;// 退款编号
	private String reason;// 退款原因
	private String instruction;// 退款说明
	private BigDecimal money;// 退款金额
	private String images;// 退款上传图片

	public String getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getInstruction() {
		return instruction;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	@Override
	public String toString() {
		return "Refund [orderDetailId=" + orderDetailId + ", reason=" + reason + ", instruction=" + instruction
				+ ", money=" + money + ", images=" + images + "]";
	}

}
