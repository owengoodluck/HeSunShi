package com.xseagull.boxing.features.entity;

public class BoxType {
	private long id;
	private long classifyId;
	private String classifyName;
	private String image;
	private Integer maxPiece = 0;
	private Integer minPiece = 0;
	private Integer reserveNum = 0;
	private String name;
	private String remark;
	private String valuationFormula;
	private String status;

	public Integer getReserveNum() {
		return reserveNum;
	}

	public void setReserveNum(Integer reserveNum) {
		this.reserveNum = reserveNum;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getClassifyName() {
		return classifyName;
	}

	public void setClassifyName(String classifyName) {
		this.classifyName = classifyName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getClassifyId() {
		return classifyId;
	}

	public void setClassifyId(long classifyId) {
		this.classifyId = classifyId;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getMaxPiece() {
		return maxPiece;
	}

	public void setMaxPiece(Integer maxPiece) {
		this.maxPiece = maxPiece;
	}

	public Integer getMinPiece() {
		return minPiece;
	}

	public void setMinPiece(Integer minPiece) {
		this.minPiece = minPiece;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getValuationFormula() {
		return valuationFormula;
	}

	public void setValuationFormula(String valuationFormula) {
		this.valuationFormula = valuationFormula;
	}

	@Override
	public String toString() {
		return "BoxType [id=" + id + ", classifyId=" + classifyId + ", classifyName=" + classifyName + ", image="
				+ image + ", maxPiece=" + maxPiece + ", minPiece=" + minPiece + ", name=" + name + ", remark=" + remark
				+ ", valuationFormula=" + valuationFormula + ", status=" + status + "]";
	}

}
