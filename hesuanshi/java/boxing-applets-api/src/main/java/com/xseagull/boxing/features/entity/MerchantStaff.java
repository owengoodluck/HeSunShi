package com.xseagull.boxing.features.entity;

import java.util.Date;

/**
 * 商户人员信息
 */
public class MerchantStaff {
	/**
	 * id
	 */
	private long id;
	/**
	 * 商户id
	 */
	private long merchantId;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 微信开放平台唯一值
	 */
	private String unionid;
	/**
	 * 微信服务号openid
	 */
	private String serviceNumberOpenid;
	/**
	 * 类型(1:店主;2:店员;)
	 */
	private int type;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;

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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
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

	@Override
	public String toString() {
		return "MerchantStaff [id=" + id + ", merchantId=" + merchantId + ", phone=" + phone + ", contact=" + contact
				+ ", unionid=" + unionid + ", serviceNumberOpenid=" + serviceNumberOpenid + ", type=" + type
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}

}
