package com.xseagull.boxing.features.entity;

/**
 *  计算金额
 */
public class Calculated {
	/**
	 *  包装盒型id
	 */
	private long typeId;
	/**
	 *  包装材质id
	 */
	private long materialId;
	/**
	 *  包装版面id
	 */
	private long mayoutId;
	/**
	 *  印刷方式id
	 */
	private long printMethodId;
	/**
	 *  长
	 */
	private double length;
	/**
	 *  宽
	 */
	private double width;
	/**
	 *  高
	 */
	private double height;
	/**
	 *  件数
	 */
	private int piece;

	/**
	 * 预付款金额
	 */
	private int advancePaymentPrice;
	
	public long getTypeId() {
		return typeId;
	}
	public void setTypeId(long typeId) {
		this.typeId = typeId;
	}
	public long getMaterialId() {
		return materialId;
	}
	public void setMaterialId(long materialId) {
		this.materialId = materialId;
	}
	public long getMayoutId() {
		return mayoutId;
	}
	public void setMayoutId(long mayoutId) {
		this.mayoutId = mayoutId;
	}
	public long getPrintMethodId() {
		return printMethodId;
	}
	public void setPrintMethodId(long printMethodId) {
		this.printMethodId = printMethodId;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
	public double getWidth() {
		return width;
	}
	public void setWidth(double width) {
		this.width = width;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}

	public int getAdvancePaymentPrice() {
		return advancePaymentPrice;
	}

	public void setAdvancePaymentPrice(int advancePaymentPrice) {
		this.advancePaymentPrice = advancePaymentPrice;
	}
}
