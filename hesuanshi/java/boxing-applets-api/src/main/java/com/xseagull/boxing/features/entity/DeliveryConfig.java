package com.xseagull.boxing.features.entity;

/**
 *  配送配置
 */
public class DeliveryConfig {
	/**
	 *  id
	 */
	private long id;
	/**
	 *  公式
	 */
	private String formula;
	/**
	 *  首重重量
	 */
	private String firstWeight;
	/**
	 *  续重重量
	 */
	private String continuedWeight;
	/**
	 *  首重费用
	 */
	private String firstCost;
	/**
	 *  续重费用
	 */
	private String continuedCost;
	
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
	public String getFirstWeight() {
		return firstWeight;
	}
	public void setFirstWeight(String firstWeight) {
		this.firstWeight = firstWeight;
	}
	public String getContinuedWeight() {
		return continuedWeight;
	}
	public void setContinuedWeight(String continuedWeight) {
		this.continuedWeight = continuedWeight;
	}
	public String getFirstCost() {
		return firstCost;
	}
	public void setFirstCost(String firstCost) {
		this.firstCost = firstCost;
	}
	public String getContinuedCost() {
		return continuedCost;
	}
	public void setContinuedCost(String continuedCost) {
		this.continuedCost = continuedCost;
	}
	@Override
	public String toString() {
		return "DeliveryConfig [id=" + id + ", formula=" + formula + ", firstWeight=" + firstWeight
				+ ", continuedWeight=" + continuedWeight + ", firstCost=" + firstCost + ", continuedCost="
				+ continuedCost + "]";
	}
	
	
}
