package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;

public class RefundVO {

	private long id;// '主键',
	private long userId;// '用户id',
	private String nickName;// '用户名',
	private String phone;// '用户手机',
	private long orderId;// '订单id',
	private long orderDetailId;// '订单详情id',
	private String refundNumber;// '退款编号',
	private String reason;// 退款原因',
	private String instruction;// '退款说明',
	private BigDecimal money;// '退款金额',
	private String status;// '状态(0:未退款;1:退款成功;2:退款失败;3:退款中)',

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public String getRefundNumber() {
		return refundNumber;
	}

	public void setRefundNumber(String refundNumber) {
		this.refundNumber = refundNumber;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RefundVO [id=" + id + ", userId=" + userId + ", nickName=" + nickName + ", phone=" + phone
				+ ", orderDetailId=" + orderDetailId + ", refundNumber=" + refundNumber + ", reason=" + reason
				+ ", instruction=" + instruction + ", money=" + money + ", status=" + status + "]";
	}

}
