package com.xseagull.boxing.features.vo;

public class MerchantStaffVO {

	private long id;
	private long merchantId;
	private String phone;
	private String contact;
	private String unionid;
	private String serviceNumberOpenid;
	private String createTime;

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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

}
