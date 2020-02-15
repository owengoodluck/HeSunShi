package com.xseagull.boxing.pay;

public class OrderDetail {
	private String book_id;
	private long pay_time;
	private String pay_tradeno;
	private double real_price;
	public String getBook_id() {
		return book_id;
	}
	public void setBook_id(String book_id) {
		this.book_id = book_id;
	}
	public long getPay_time() {
		return pay_time;
	}
	public void setPay_time(long pay_time) {
		this.pay_time = pay_time;
	}
	public String getPay_tradeno() {
		return pay_tradeno;
	}
	public void setPay_tradeno(String pay_tradeno) {
		this.pay_tradeno = pay_tradeno;
	}
	public double getReal_price() {
		return real_price;
	}
	public void setReal_price(double real_price) {
		this.real_price = real_price;
	}
}
