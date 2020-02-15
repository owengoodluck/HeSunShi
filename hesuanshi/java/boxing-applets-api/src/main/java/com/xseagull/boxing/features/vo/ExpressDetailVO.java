package com.xseagull.boxing.features.vo;

/**
 * 物流信息返回的VO
 * 
 * @author Administrator
 *
 */
public class ExpressDetailVO {

	private String serial;// 快递单号
	private String icon;// 快递logo
	private String phone;// 快递官方电话
	private String type;// 快递别名
	private String name;// 快递名称
	private String address;// 收货地址

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
