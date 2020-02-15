package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class WalletRecord {
	private long id;// '主键',
	private String businessNumber;// '交易编号',
	private int type;// '类型(0:充值;1:提现;)',
	private BigDecimal money;// '金额',
	private int status;// '状态(0:未成功;1:成功;)',
	private long userId;// '用户id',
	private String createTime;// '创建时间',

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

}
