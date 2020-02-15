package com.xseagull.boxing.features.entity;

import java.util.Date;

/**
 * 用户信息
 */
public class User {
	/**
	 * id
	 */
	private long id;
	/**
	 * 头像图片
	 */
	private String avatar;
	/**
	 * 昵称
	 */
	private String nickName;
	/**
	 * 性别(0:未知;1:男;2:女;)
	 */
	private String gender;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 市区
	 */
	private String city;
	/**
	 * 微信小程序openid
	 */
	private String openid;
	/**
	 * 育儿小程序openid
	 */
	private String yuerOpenid;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 推荐人id
	 */
	private long recommenderId;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 糖币值
	 */
	private int quantity;
	/**
	 * 来源
	 */
	private String source;
	/**
	 * 微信开放平台唯一值
	 */
	private String unionid;
	/**
	 * 微信服务号openid
	 */
	private String serviceNumberOpenid;

	private String code;
	private String iv;
	private String encrypted;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getIv() {
		return iv;
	}

	public void setIv(String iv) {
		this.iv = iv;
	}

	public String getEncrypted() {
		return encrypted;
	}

	public void setEncrypted(String encrypted) {
		this.encrypted = encrypted;
	}

	public String getYuerOpenid() {
		return yuerOpenid;
	}

	public void setYuerOpenid(String yuerOpenid) {
		this.yuerOpenid = yuerOpenid;
	}

	public long getRecommenderId() {
		return recommenderId;
	}

	public void setRecommenderId(long recommenderId) {
		this.recommenderId = recommenderId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getUnionid() {
		return unionid;
	}

	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}

	public String getServiceNumberOpenid() {
		return serviceNumberOpenid;
	}

	public void setServiceNumberOpenid(String serviceNumberOpenid) {
		this.serviceNumberOpenid = serviceNumberOpenid;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", avatar=" + avatar + ", nickName=" + nickName + ", gender=" + gender + ", province="
				+ province + ", city=" + city + ", openid=" + openid + ", yuerOpenid=" + yuerOpenid + ", phone=" + phone
				+ ", createTime=" + createTime + ", recommenderId=" + recommenderId + ", updateTime=" + updateTime
				+ ", quantity=" + quantity + ", source=" + source + ", unionid=" + unionid + ", serviceNumberOpenid="
				+ serviceNumberOpenid + ", code=" + code + ", iv=" + iv + ", encrypted=" + encrypted + "]";
	}

}
