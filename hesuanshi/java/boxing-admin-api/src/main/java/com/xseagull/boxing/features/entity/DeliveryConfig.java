package com.xseagull.boxing.features.entity;

public class DeliveryConfig {

	private long id;// 主键',
	private String formula;// '公式',
	private double firstWeight = 0.00;// '首重重量',
	private double continuedWeight = 0.00;// '续重重量',
	private double firstCost = 0.00;// '首重费用',
	private double continuedCost = 0.00;// '续重费用',

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFormula() {
		return formula;
	}

	public void setFormula(String formula) {
		this.formula = formula;
	}

	public double getFirstWeight() {
		return firstWeight;
	}

	public void setFirstWeight(double firstWeight) {
		this.firstWeight = firstWeight;
	}

	public double getContinuedWeight() {
		return continuedWeight;
	}

	public void setContinuedWeight(double continuedWeight) {
		this.continuedWeight = continuedWeight;
	}

	public double getFirstCost() {
		return firstCost;
	}

	public void setFirstCost(double firstCost) {
		this.firstCost = firstCost;
	}

	public double getContinuedCost() {
		return continuedCost;
	}

	public void setContinuedCost(double continuedCost) {
		this.continuedCost = continuedCost;
	}

	@Override
	public String toString() {
		return "DeliveryConfig [id=" + id + ", formula=" + formula + ", firstWeight=" + firstWeight
				+ ", continuedWeight=" + continuedWeight + ", firstCost=" + firstCost + ", continuedCost="
				+ continuedCost + "]";
	}

}
