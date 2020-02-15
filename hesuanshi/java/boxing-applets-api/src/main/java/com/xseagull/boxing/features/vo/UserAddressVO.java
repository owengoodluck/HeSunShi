package com.xseagull.boxing.features.vo;

public class UserAddressVO {

	private long id;// 主键id
	private long userId;// 用户
	private String name;// 姓名
	private String phone;// 手机号
	private String province;// 省份
	private String city;// 城市
	private String county;// 县区
	private String address;// 详情地址
	private Integer status;// 状态(0不是默认,1是默认地址)

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserAddressVO [id=" + id + ", userId=" + userId + ", name=" + name + ", phone=" + phone + ", province="
				+ province + ", city=" + city + ", county=" + county + ", address=" + address + ", status=" + status
				+ "]";
	}

}
