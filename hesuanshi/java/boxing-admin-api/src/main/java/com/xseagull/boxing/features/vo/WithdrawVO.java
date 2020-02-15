package com.xseagull.boxing.features.vo;

import java.math.BigDecimal;

public class WithdrawVO {

	private long id;// '主键',
	private long uId;// '用户id',
	private String name;// '用户真实姓名',
	private String nickName;// 微信名',
	private String phone;// '用户手机',
	private BigDecimal money;// '提现金额',
	private String status;// '提现状态(0:待处理;1:处理中,2:已处理)',
	private String time;// 更新时间,
	private String formId;// 通知ID,
	private String openId;// 服务号openId,

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getuId() {
		return uId;
	}

	public void setuId(long uId) {
		this.uId = uId;
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

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	@Override
	public String toString() {
		return "WithdrawVO [id=" + id + ", uId=" + uId + ", name=" + name + ", phone=" + phone + ", money=" + money
				+ ", status=" + status + ", time=" + time + ", formId=" + formId + "]";
	}

}
