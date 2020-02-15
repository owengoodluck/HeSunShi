package com.xseagull.boxing.pay;

import java.util.List;

public class Order {
	private String platform_key;
	private String sign;
	private String user_id;
	private int flag;
	private List<OrderDetail> order_info;
	
	public String getPlatform_key() {
		return platform_key;
	}
	public void setPlatform_key(String platform_key) {
		this.platform_key = platform_key;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getFlag() {
		return flag;
	}
	public void setFlag(int flag) {
		this.flag = flag;
	}
	public List<OrderDetail> getOrder_info() {
		return order_info;
	}
	public void setOrder_info(List<OrderDetail> order_info) {
		this.order_info = order_info;
	}
}
