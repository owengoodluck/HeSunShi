package com.xseagull.boxing.features.entity;

public class ResourceFile {
	private long id;
	private String type;
	private String name;
	private String resourceNumber;
	private String file;
	private String createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getResourceNumber() {
		return resourceNumber;
	}

	public void setResourceNumber(String resourceNumber) {
		this.resourceNumber = resourceNumber;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
}
