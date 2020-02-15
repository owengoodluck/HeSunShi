package com.xseagull.boxing.features.dto;

import java.util.List;

public class MenuDTO {

	private String id;// id
	private String pid;// 父id
	private String label;// 菜单名
	private List<MenuDTO> children;

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

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<MenuDTO> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDTO> children) {
		this.children = children;
	}

	@Override
	public String toString() {
		return "TestMenuDTO [id=" + id + ", pid=" + pid + ", label=" + label + ", children=" + children + "]";
	}

}
