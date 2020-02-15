package com.xseagull.boxing.features.entity;

import java.util.Date;

/**
* 手机验证码
*/
public class PhoneCode {
	/**
	 * id
	 */
	private long id;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * 验证码
	 */
	private String code;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 过期时间
	 */
	private Date endTime;
	
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
