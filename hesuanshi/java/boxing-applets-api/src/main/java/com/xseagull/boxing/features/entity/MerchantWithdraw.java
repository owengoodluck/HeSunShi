package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class MerchantWithdraw {
	private long id;// '主键',
	private long merchantId;// '商户户id',
	private BigDecimal money;// '提现金额',
	private String status;// '提现状态(0:待处理;1:处理中,2:已处理)',
	private String createTime;// 创建时间,

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(long merchantId) {
		this.merchantId = merchantId;
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
