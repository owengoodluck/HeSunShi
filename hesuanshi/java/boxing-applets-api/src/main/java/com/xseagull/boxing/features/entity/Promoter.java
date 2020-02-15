package com.xseagull.boxing.features.entity;

/**
 * 推广人员信息
 * 
 * @author Administrator
 *
 */
public class Promoter {

	private long id;// 主键',
	private long userId;// '用户id',
	private String phone;// '手机号码',
	private String realName;// '姓名',
	private String content;// '资源优势',
	private double commissionRate;// 佣金比例',
	private String status;// '审核状态 : 0:待审核; 1: 通过; 2: 失败',
	private String formId;// 通知id,

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Promoter [id=" + id + ", userId=" + userId + ", phone=" + phone + ", realName=" + realName
				+ ", content=" + content + ", status=" + status + "]";
	}

}
