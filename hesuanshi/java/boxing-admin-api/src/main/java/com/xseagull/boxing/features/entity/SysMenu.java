package com.xseagull.boxing.features.entity;

public class SysMenu {

	private String id;// id
	private String pid;// 父id
	private String name;// 菜单名
	private String pName;// 父级菜单名
	private String power;// 权限
	private String ismenu;// 是否菜单/按钮
	private String tips;// 备注
	private String status;// 状态

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getIsmenu() {
		return ismenu;
	}

	public void setIsmenu(String ismenu) {
		this.ismenu = ismenu;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SysMenu [id=" + id + ", pid=" + pid + ", name=" + name + ", pName=" + pName + ", power=" + power
				+ ", ismenu=" + ismenu + ", tips=" + tips + ", status=" + status + "]";
	}

}
