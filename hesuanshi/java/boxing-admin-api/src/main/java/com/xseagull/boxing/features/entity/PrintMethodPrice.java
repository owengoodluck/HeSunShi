package com.xseagull.boxing.features.entity;

import java.math.BigDecimal;

public class PrintMethodPrice {

	private long id;
	private long printMethodId;// 印刷方式id
	private String printMethodName;// 印刷方式名称
	private BigDecimal price;// 价格
	private Integer piece;// 件数

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPrintMethodId() {
		return printMethodId;
	}

	public void setPrintMethodId(long printMethodId) {
		this.printMethodId = printMethodId;
	}

	public String getPrintMethodName() {
		return printMethodName;
	}

	public void setPrintMethodName(String printMethodName) {
		this.printMethodName = printMethodName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getPiece() {
		return piece;
	}

	public void setPiece(Integer piece) {
		this.piece = piece;
	}

}
