package com.xseagull.boxing.features.entity;

public class SysDept {

	private String id;
	private String deptName;
	private String remark;

	@Override
	public String toString() {
		return "TestDept [id=" + id + ", deptName=" + deptName + ", remark=" + remark + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
