package com.xseagull.boxing.features.entity;

public class SysConfig {

	private String id;
	private String itemKey;
	private String itemValue;
	private String createTime;// '创建时间',
	private String updateTime;// '更新时间',
	private String remark;

	@Override
	public String toString() {
		return "TestDept [id=" + id + ", itemKey=" + itemKey+ ", itemValue=" + itemValue+ ", remark=" + remark+  "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getItemKey() {
		return itemKey;
	}

	public void setItemKey(String itemKey) {
		this.itemKey = itemKey;
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
