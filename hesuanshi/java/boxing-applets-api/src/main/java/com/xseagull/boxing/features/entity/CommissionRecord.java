package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

/**
 * 佣金记录
 */
public class CommissionRecord {

	private long id;// '主键',
	private long userId;// '用户id',
	private long orderDetailId;// '订单详情id',
	private BigDecimal price;// '实际支付金额(可包含运费)',
	private BigDecimal commission;// '佣金',
	private int status;// '状态(0:未成功;1:可提现;2:提现中;3:已提现;)',
	private String specificationName;// 方案名称,
	private String time;// 创建时间,

	public String getSpecificationName() {
		return specificationName;
	}

	public void setSpecificationName(String specificationName) {
		this.specificationName = specificationName;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
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

	public long getOrderDetailId() {
		return orderDetailId;
	}

	public void setOrderDetailId(long orderDetailId) {
		this.orderDetailId = orderDetailId;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
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

}
