package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;
import java.util.List;

public class MerchantOrderVO {

	private long id;// 主键',
	private long orderId;// 订单id',
	private String orderNumber;// 订单编号',
	private String businessNumber;// 交易编号',
	private String createTime;// 下单时间,
	private String userRemark;// 买家留言,
	private String merchantRemark;// 商家备注,
	private String resourcePath;// 文件下载地址,
	private int fareType;// 运费支付方式(0: 寄方付;1: 买方付),
	private BigDecimal countPrice = BigDecimal.ZERO;// 合计
	private BigDecimal money = BigDecimal.ZERO;// 该订单可得金额
	private List<OrderDetailVO> orderDetailVO;// 订单详情

	public BigDecimal getMoney() {
		return money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(String businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUserRemark() {
		return userRemark;
	}

	public void setUserRemark(String userRemark) {
		this.userRemark = userRemark;
	}

	public String getMerchantRemark() {
		return merchantRemark;
	}

	public void setMerchantRemark(String merchantRemark) {
		this.merchantRemark = merchantRemark;
	}

	public int getFareType() {
		return fareType;
	}

	public void setFareType(int fareType) {
		this.fareType = fareType;
	}

	public BigDecimal getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(BigDecimal countPrice) {
		this.countPrice = countPrice;
	}

	public List<OrderDetailVO> getOrderDetailVO() {
		return orderDetailVO;
	}

	public void setOrderDetailVO(List<OrderDetailVO> orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
	}

}
