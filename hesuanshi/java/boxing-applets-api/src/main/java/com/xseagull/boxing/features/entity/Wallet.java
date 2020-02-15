package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class Wallet {
	private long id;// '主键',
	private long userId;// '用户id',
	private BigDecimal money;// '金额',

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

	@Override
	public String toString() {
		return "Wallet [id=" + id + ", userId=" + userId + ", money=" + money + "]";
	}

}
