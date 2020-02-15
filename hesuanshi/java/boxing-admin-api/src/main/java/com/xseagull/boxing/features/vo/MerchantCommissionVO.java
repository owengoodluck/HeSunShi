package com.xseagull.boxing.features.vo;

public class MerchantCommissionVO {

	private long id;
	private long merchantId;
	private long orderId;
	private long merchantWithdrawId;
	private double commission;
	private String status;
	private String createTime;

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

	public long getMerchantWithdrawId() {
		return merchantWithdrawId;
	}

	public void setMerchantWithdrawId(long merchantWithdrawId) {
		this.merchantWithdrawId = merchantWithdrawId;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
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
