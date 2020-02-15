package com.xseagull.boxing.features.entity;

/**
 * 商户登录信息
 */
public class MerchantLogin {
	/**
	 * 手机号码
	 */
	private String phone;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 微信开放平台唯一值
	 */
	private String unionid;
	/**
	 * 微信服务号openid
	 */
	private String openid;
	
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getUnionid() {
		return unionid;
	}
	public void setUnionid(String unionid) {
		this.unionid = unionid;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
}
