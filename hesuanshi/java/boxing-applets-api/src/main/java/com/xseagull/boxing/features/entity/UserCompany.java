package com.xseagull.boxing.features.entity;

/**
 * 用户信息
 */
/**
 * @author Administrator
 *
 */
public class UserCompany {

	private long id;// 主键',
	private long userId;// '用户id',
	private String companyName;// '企业名称',
	private String creditCode;// '统一社会信用代码',
	private String province;// '省份',
	private String city;// '市区',
	private String address;// '详细地址',
	private String businessLicense;// '营业执照图片地址',
	private String status;// '审核状态 : 0:待审核; 1: 通过; 2: 失败',

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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCreditCode() {
		return creditCode;
	}

	public void setCreditCode(String creditCode) {
		this.creditCode = creditCode;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBusinessLicense() {
		return businessLicense;
	}

	public void setBusinessLicense(String businessLicense) {
		this.businessLicense = businessLicense;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
