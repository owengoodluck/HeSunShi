package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

/**
 * 商户佣金记录
 */
public class MerchantCommission {

	private long id;// '主键',
	private long merchantId;// '商户id',
	private long orderId;// '订单id',
	private BigDecimal commission;// '佣金',
	private int status;// '状态(0:未成功;1:可提现;2:提现中;3:已提现;)',
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

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public BigDecimal getCommission() {
		return commission;
	}

	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "MerchantCommission [id=" + id + ", merchantId=" + merchantId + ", orderId=" + orderId + ", commission="
				+ commission + ", status=" + status + ", createTime=" + createTime + "]";
	}

}
