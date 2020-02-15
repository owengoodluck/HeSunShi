package com.xseagull.boxing.features.vo;

public class HomeDataVO {

	private String logo;// 商户logo
	private String name;// 商户名称
	private String items;// 主营项目
	private String contact;// 联系人
	private String phone;// 手机号码
	private int todayOrderNum = 0;// 今日订单数,
	private int myStaffNum = 0;// 我的店员数,
	private int pendingOrderNum = 0;// 待确定订单数,
	private int pendingShipmentOrderNum = 0;// 待发货订单数,
	private int activistOrderNum = 0;// 维权订单数,

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public int getTodayOrderNum() {
		return todayOrderNum;
	}

	public void setTodayOrderNum(int todayOrderNum) {
		this.todayOrderNum = todayOrderNum;
	}

	public int getMyStaffNum() {
		return myStaffNum;
	}

	public void setMyStaffNum(int myStaffNum) {
		this.myStaffNum = myStaffNum;
	}

	public int getPendingOrderNum() {
		return pendingOrderNum;
	}

	public void setPendingOrderNum(int pendingOrderNum) {
		this.pendingOrderNum = pendingOrderNum;
	}

	public int getPendingShipmentOrderNum() {
		return pendingShipmentOrderNum;
	}

	public void setPendingShipmentOrderNum(int pendingShipmentOrderNum) {
		this.pendingShipmentOrderNum = pendingShipmentOrderNum;
	}

	public int getActivistOrderNum() {
		return activistOrderNum;
	}

	public void setActivistOrderNum(int activistOrderNum) {
		this.activistOrderNum = activistOrderNum;
	}

	@Override
	public String toString() {
		return "HomeDataVO [logo=" + logo + ", name=" + name + ", items=" + items + ", todayOrderNum=" + todayOrderNum
				+ ", myStaffNum=" + myStaffNum + ", pendingOrderNum=" + pendingOrderNum + ", pendingShipmentOrderNum="
				+ pendingShipmentOrderNum + ", activistOrderNum=" + activistOrderNum + "]";
	}

}
