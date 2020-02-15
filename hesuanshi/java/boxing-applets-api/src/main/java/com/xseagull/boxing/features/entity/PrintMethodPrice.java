package com.xseagull.boxing.features.entity;

/**
 *  印刷价格
 */
public class PrintMethodPrice {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  件数
	 */
	private int piece;
	/**
	 *  价格
	 */
	private double price;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getPiece() {
		return piece;
	}
	public void setPiece(int piece) {
		this.piece = piece;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
}
