package com.xseagull.boxing.features.entity;

public class BoxMaterialClassify {

	private long id;
	private String name;
	private String status;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BoxTypeClassify [id=" + id + ", name=" + name + ", status=" + status + "]";
	}

}
