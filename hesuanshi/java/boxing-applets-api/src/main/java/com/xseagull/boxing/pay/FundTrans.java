package com.xseagull.boxing.pay;

public class FundTrans {
	/**
     * 商户系统内部的订单号
    */
    private String outBizNo;
    
    /**
     * 账号
    */
    private String account;
    
    /**
     * 订单总金额，单位为元
    */
    private String totalAmount;
    
    /**
     * 转账备注
    */
    private String remark;
    
    /**
     * 收款用户真实姓名
    */
    private String realName;

	public String getOutBizNo() {
		return outBizNo;
	}

	public void setOutBizNo(String outBizNo) {
		this.outBizNo = outBizNo;
	}
	
	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}
}
