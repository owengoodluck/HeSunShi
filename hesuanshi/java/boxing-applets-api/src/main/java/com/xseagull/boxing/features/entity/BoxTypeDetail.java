package com.xseagull.boxing.features.entity;

/**
 *  包装盒型
 */
public class BoxTypeDetail {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  名称
	 */
	private String name;
	/**
	 *  图片
	 */
	private String image;
	/**
	 *  最小订购数量
	 */
	private int minPiece;
	/**
	 *  最大订购数量
	 */
	private int maxPiece;
	/**
	 *  备注
	 */
	private String remark;
	/**
	 *  计价公式
	 */
	private String valuationFormula;
	/**
	 *  预留量
	 */
	private int reserveNum;
	
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
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getMinPiece() {
		return minPiece;
	}
	public void setMinPiece(int minPiece) {
		this.minPiece = minPiece;
	}
	public int getMaxPiece() {
		return maxPiece;
	}
	public void setMaxPiece(int maxPiece) {
		this.maxPiece = maxPiece;
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
	public int getReserveNum() {
		return reserveNum;
	}
	public void setReserveNum(int reserveNum) {
		this.reserveNum = reserveNum;
	}
}
