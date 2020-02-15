package com.xseagull.boxing.features.entity;

/**
 * 商户订单
 */
public class MerchantOrder {
	private long id;// '主键',
	private long merchantId;// '商户ID',
	private long orderId;// '订单id',
	private double money;// '该订单可获得金额',
	private String remark;// '备注',
	private String status;// '状态(0:未处理;1:统一;2:驳回;)',
	private String reason;// '驳回理由',
	private String createTime;// '创建时间',

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

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
