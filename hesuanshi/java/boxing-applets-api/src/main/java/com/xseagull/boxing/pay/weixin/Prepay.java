package com.xseagull.boxing.pay.weixin;

public class Prepay {
	/**
	 * 应用ID
    */
	private String appid;
	/**
	 * 商户号
    */
	private String mch_id;
	/**
	 * 随机字符串
    */
	private String nonce_str;
	/**
	 * 签名
    */
	private String sign;
	/**
	 * 预支付交易会话ID
    */
	private String prepay_id;
	/**
	 * 时间戳
    */
	private String timestamp;
	/**
	 * 扩展字段
    */
	private String extended;
	
	private String outTradeNo;
	
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getMch_id() {
		return mch_id;
	}
	public void setMch_id(String mch_id) {
		this.mch_id = mch_id;
	}
	public String getNonce_str() {
		return nonce_str;
	}
	public void setNonce_str(String nonce_str) {
		this.nonce_str = nonce_str;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getPrepay_id() {
		return prepay_id;
	}
	public void setPrepay_id(String prepay_id) {
		this.prepay_id = prepay_id;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getExtended() {
		return extended;
	}
	public void setExtended(String extended) {
		this.extended = extended;
	}
	public String getOutTradeNo() {
		return outTradeNo;
	}
	public void setOutTradeNo(String outTradeNo) {
		this.outTradeNo = outTradeNo;
	}
}
