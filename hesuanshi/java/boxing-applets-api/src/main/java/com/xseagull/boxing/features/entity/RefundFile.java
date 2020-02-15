package com.xseagull.boxing.features.entity;

/**
 * 退款附件
 */
public class RefundFile {

	private long id;// 主键id
	private long refundId;// 退款表id
	private String resource;// 退款图片

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRefundId() {
		return refundId;
	}

	public void setRefundId(long refundId) {
		this.refundId = refundId;
	}

	public String getResource() {
		return resource;
	}

	public void setResource(String resource) {
		this.resource = resource;
	}

	@Override
	public String toString() {
		return "RefundFile [id=" + id + ", refundId=" + refundId + ", resource=" + resource + "]";
	}

}
