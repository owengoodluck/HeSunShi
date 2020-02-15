package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;
import java.util.List;

public class CustomOrderVO {

	private long id;// 主键',
	private long userId;// 用户id',
	private String orderNumber;// 订单编号',
	private String businessNumber;// 交易编号',
	private String contactName;// 买家姓名',
	private String contactPhone;// 买家手机号',
	private String contactAddress;// 买家地址',
	//状态(0:待付定金;90:待派单;900:待同意派单；91:待发货（样品）;92:待收货（样品）;93:等付尾款;93:等付全款(二次下单，直接支付全款发货);
	// 1:待发货;2:待收货;3:待评价;4:交易成功;5:已取消;6:已删除;7:退款中)'
	private String status;
	private String remark;// 买家留言,
	private String createTime;// 下单时间,
	private String shipTime;// 发货时间,
	private int fareType;// 运费支付方式(0: 寄方付;1: 买方付),
	private BigDecimal countPrice = BigDecimal.ZERO;// 合计


	private List<OrderDetailVO> orderDetailVO;// 订单详情

	public String getShipTime() {
		return shipTime;
	}

	public void setShipTime(String shipTime) {
		this.shipTime = shipTime;
	}

	public int getFareType() {
		return fareType;
	}

	public void setFareType(int fareType) {
		this.fareType = fareType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getCountPrice() {
		return countPrice;
	}

	public void setCountPrice(BigDecimal countPrice) {
		this.countPrice = countPrice;
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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactAddress() {
		return contactAddress;
	}

	public void setContactAddress(String contactAddress) {
		this.contactAddress = contactAddress;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderDetailVO> getOrderDetailVO() {
		return orderDetailVO;
	}

	public void setOrderDetailVO(List<OrderDetailVO> orderDetailVO) {
		this.orderDetailVO = orderDetailVO;
	}

	@Override
	public String toString() {
		return "CustomOrderVO [id=" + id + ", userId=" + userId + ", orderNumber=" + orderNumber + ", businessNumber="
				+ businessNumber + ", contactName=" + contactName + ", contactPhone=" + contactPhone
				+ ", contactAddress=" + contactAddress + ", status=" + status + ", remark=" + remark + ", countPrice="
				+ countPrice + ", orderDetailVO=" + orderDetailVO + "]";
	}

}
