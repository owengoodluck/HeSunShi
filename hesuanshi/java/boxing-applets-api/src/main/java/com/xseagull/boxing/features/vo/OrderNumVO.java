package com.xseagull.boxing.features.vo;

/**
 * 我的页面不同状态订单数VO
 * 
 * @author Administrator
 *
 */
public class OrderNumVO {

	private int pendingPay;
	private int pendingDelivery;
	private int pendingReceipt;
	private int refund;

	public int getPendingPay() {
		return pendingPay;
	}

	public void setPendingPay(int pendingPay) {
		this.pendingPay = pendingPay;
	}

	public int getPendingDelivery() {
		return pendingDelivery;
	}

	public void setPendingDelivery(int pendingDelivery) {
		this.pendingDelivery = pendingDelivery;
	}

	public int getPendingReceipt() {
		return pendingReceipt;
	}

	public void setPendingReceipt(int pendingReceipt) {
		this.pendingReceipt = pendingReceipt;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}

}
