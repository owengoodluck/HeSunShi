package com.xseagull.boxing.features.entity;

public class SysRole {

	private String id;
	private String roleName;
	private String remark;
	private String empower;

	public String getEmpower() {
		return empower;
	}

	public void setEmpower(String empower) {
		this.empower = empower;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "TestRole [id=" + id + ", roleName=" + roleName + ", remark=" + remark + ", empower=" + empower + "]";
	}

}
