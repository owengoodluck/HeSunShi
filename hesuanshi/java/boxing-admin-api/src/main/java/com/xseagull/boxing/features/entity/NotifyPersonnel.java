package com.xseagull.boxing.features.entity;

/**
 * 通知人员
 */
public class NotifyPersonnel {
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
	 * 手机号码
	 */
	private String phone;
	/**
	 * 创建时间
	 */
	private String createTime;

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

}
