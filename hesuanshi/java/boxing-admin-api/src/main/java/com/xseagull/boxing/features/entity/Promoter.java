package com.xseagull.boxing.features.entity;

/**
 * 推广人员
 */
/**
 * @author Administrator
 *
 */
public class Promoter {
	/**
	 * id
	 */
	private long id;
	/**
	 * 用户id
	 */
	private long uId;
	/**
	 * 头像图片
	 */
	private String avatar;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 真实姓名
	 */
	private String realName;

	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 资源优势
	 */
	private String content;
	/**
	 * 佣金比例
	 */
	private double commissionRate = 0.00;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 状态
	 * 
	 * @return
	 */
	private String status;
	private String formId;
	private String remark;

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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

	public double getCommissionRate() {
		return commissionRate;
	}

	public void setCommissionRate(double commissionRate) {
		this.commissionRate = commissionRate;
	}

	@Override
	public String toString() {
		return "Promoter [id=" + id + ", uId=" + uId + ", avatar=" + avatar + ", nickName=" + nickName + ", realName="
				+ realName + ", phone=" + phone + ", content=" + content + ", commissionRate=" + commissionRate
				+ ", createTime=" + createTime + ", status=" + status + ", formId=" + formId + ", remark=" + remark
				+ "]";
	}

}
