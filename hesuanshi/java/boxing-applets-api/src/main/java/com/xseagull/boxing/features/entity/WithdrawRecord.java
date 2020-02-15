package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class WithdrawRecord {
	private long id;// '主键',
	private long userId;// '用户id',
	private BigDecimal money;// '提现金额',
	private String status;// '提现状态(0:待处理;1:处理中,2:已处理)',
	private String time;// 更新时间,
	private String formId;// 通知ID,

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

}
