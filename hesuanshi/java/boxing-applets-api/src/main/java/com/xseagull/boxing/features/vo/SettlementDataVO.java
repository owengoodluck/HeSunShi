package com.xseagull.boxing.features.vo;

public class SettlementDataVO {

	private double totalCommission = 0.00;// 累计收益',
	private double pendingCommission = 0.00;// 累计待结算',
	private double canCommission = 0.00;// 可提现佣金',
	private double yesterdayCommission = 0.00;// 昨日收益',
	private int unconfirmed = 0;// 未确认订单',
	private int confirmed = 0;// 已确认订单',

	public double getTotalCommission() {
		return totalCommission;
	}

	public void setTotalCommission(double totalCommission) {
		this.totalCommission = totalCommission;
	}

	public double getPendingCommission() {
		return pendingCommission;
	}

	public void setPendingCommission(double pendingCommission) {
		this.pendingCommission = pendingCommission;
	}

	public double getCanCommission() {
		return canCommission;
	}

	public void setCanCommission(double canCommission) {
		this.canCommission = canCommission;
	}

	public double getYesterdayCommission() {
		return yesterdayCommission;
	}

	public void setYesterdayCommission(double yesterdayCommission) {
		this.yesterdayCommission = yesterdayCommission;
	}

	public int getUnconfirmed() {
		return unconfirmed;
	}

	public void setUnconfirmed(int unconfirmed) {
		this.unconfirmed = unconfirmed;
	}

	public int getConfirmed() {
		return confirmed;
	}

	public void setConfirmed(int confirmed) {
		this.confirmed = confirmed;
	}

	@Override
	public String toString() {
		return "SettlementDataVO [totalCommission=" + totalCommission + ", pendingCommission=" + pendingCommission
				+ ", canCommission=" + canCommission + ", yesterdayCommission=" + yesterdayCommission + ", unconfirmed="
				+ unconfirmed + ", confirmed=" + confirmed + "]";
	}

}
