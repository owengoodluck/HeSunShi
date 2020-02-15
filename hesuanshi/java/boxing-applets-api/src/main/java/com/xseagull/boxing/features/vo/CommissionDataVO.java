package com.xseagull.boxing.features.vo;

public class CommissionDataVO {

	private double totalCommission;// 累计收益',
	private double pendingCommission;// 累计待结算',
	private double canCommission;// 可提现佣金',
	private double yesterdayCommission;// 昨日收益',
	private double yesterdayPending;// 昨日待结算',
	private int yesterdayInvite;// 昨日新增邀请用户',
	private int totalInvite;// 累计邀请用户',
	private int shareNumber;// 累计分享和生成二维码次数',
	private int recordNumber;// 佣金记录条数

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

	public double getYesterdayPending() {
		return yesterdayPending;
	}

	public void setYesterdayPending(double yesterdayPending) {
		this.yesterdayPending = yesterdayPending;
	}

	public int getYesterdayInvite() {
		return yesterdayInvite;
	}

	public void setYesterdayInvite(int yesterdayInvite) {
		this.yesterdayInvite = yesterdayInvite;
	}

	public int getTotalInvite() {
		return totalInvite;
	}

	public void setTotalInvite(int totalInvite) {
		this.totalInvite = totalInvite;
	}

	public int getShareNumber() {
		return shareNumber;
	}

	public void setShareNumber(int shareNumber) {
		this.shareNumber = shareNumber;
	}

	public int getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}

}
