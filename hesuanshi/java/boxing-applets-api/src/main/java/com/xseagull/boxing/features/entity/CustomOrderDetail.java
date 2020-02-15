package com.xseagull.boxing.features.entity;

public class CustomOrderDetail extends CustomSpecification{

	private long id;// '主键',
	private long orderId;// '订单id',
//	private String type;// '类型(1:定制;2:现货;)',
//	private double price;// '价格',
//	private double fare;// '运费',
//	private String status;// '状态(0:待完成;1:已完成;2:退款中;3:已退款)',
//	private int quantity;// '数量',

//	private String formId;// 通知id
//	private String specificationName;// 方案名称
//	private String typeName;// 盒型名称
//	private String typeImage;// 盒型图片
//	private String materialName;// 材质名称
//	private String materialImage;// 材质图片
//	private String layoutName;// 版面名称
//	private String layoutImage;// 版面图片
//	private String printMethodName;//
//	private double length;// 长
//	private double width;// 宽
//	private double height;// 高


	@Override
	public long getId() {
		return id;
	}

	@Override
	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

}
