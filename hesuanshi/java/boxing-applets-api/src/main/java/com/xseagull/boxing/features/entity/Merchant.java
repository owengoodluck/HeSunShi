package com.xseagull.boxing.features.entity;

import java.util.Date;

/**
 * 商户信息
 */
public class Merchant {
	/**
	 * id
	 */
	private long id;
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 联系人
	 */
	private String contact;
	/**
	 * 商户logo
	 */
	private String logo;
	/**
	 * 商户名称
	 */
	private String name;
	/**
	 * 主营项目
	 */
	private String items;
	/**
	 * 营业执照
	 */
	private String license;
	/**
	 * 省份
	 */
	private String province;
	/**
	 * 市区
	 */
	private String city;
	/**
	 * 县区
	 */
	private String county;
	/**
	 * 详情地址
	 */
	private String address;
	/**
	 * 资格审查(0:未审核;1:审核通过;2:不通过;)
	 */
	private int status;
	/**
	 * 创建时间
	 */
	private String createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
	/**
	 * 微信开放平台唯一值
	 */
	private String unionid;
	/**
	 * 微信服务号openid
	 */
	private String serviceNumberOpenid;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getItems() {
		return items;
	}
	public void setItems(String items) {
		this.items = items;
	}
	public String getLicense() {
		return license;
	}
	public void setLicense(String license) {
		this.license = license;
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
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
}
