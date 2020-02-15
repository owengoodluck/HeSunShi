package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CustomOrder;
import com.xseagull.boxing.features.entity.SecondPay;

public interface IOrderService {

	ResultData submitOrder(CustomOrder customOrder);

	// 用户订单列表
	ResultData findOrderByStatus(Integer pageNo, Integer pageSize, int status, long userId);

	// 根据微信交易编号查询订单详情
	ResultData findOrderDetailByOutTradeNo(String outTradeNo);

	// 取消订单
	ResultData quxiaoOrder(long orderId);

	// 确认收货
	ResultData confirmReceipt(long orderId);

	// 付款--->列表上点付款
	ResultData secondPay(SecondPay secondPay);

	// 查询不同状态订单的条数
	ResultData findOrderNum(long userId);

	// 根据订单id查询收货信息
	ResultData selectAddressById(long id);

}
