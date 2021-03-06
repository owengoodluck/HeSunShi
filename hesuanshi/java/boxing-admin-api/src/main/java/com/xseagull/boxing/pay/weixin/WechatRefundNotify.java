package com.xseagull.boxing.pay.weixin;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="xml")
public class WechatRefundNotify implements Serializable {
	private static final long serialVersionUID = 1096045652655228316L;
	private String return_code;
	private String appid;
	private String mch_id;
	private String nonce_str;
	private String req_info;
	public String getReturn_code() {
		return return_code;
	}
	public void setReturn_code(String return_code) {
		this.return_code = return_code;
	}
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
	public String getReq_info() {
		return req_info;
	}
	public void setReq_info(String req_info) {
		this.req_info = req_info;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "WechatRefundNotify [return_code=" + return_code + ", appid=" + appid + ", mch_id=" + mch_id
				+ ", nonce_str=" + nonce_str + ", req_info=" + req_info + "]";
	}
	
}
