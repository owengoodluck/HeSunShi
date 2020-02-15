package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CustomOrder;
import com.xseagull.boxing.features.entity.SecondPay;
import com.xseagull.boxing.features.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private IOrderService orderServiceImpl;

	/**
	 * 提交订单--->生成订单记录
	 * 支付定金
	 */
	@PostMapping("/submitOrder")
	public ResultData submitOrder(CustomOrder customOrder) {
		return orderServiceImpl.submitOrder(customOrder);
	}

	/**
	 * 确认样品
	 * @param orderId
	 * @return
	 */
	@PostMapping("/confirmSample")
	public ResultData confirmSample(@RequestParam(value = "orderId") String orderId ,Boolean confirmResult, long userId) {
		return null;
	}


	/**
	 * 支付尾款
	 * @param orderId
	 * @param userId
	 * @return
	 */
	@PostMapping("/payRest")
	public ResultData payRest(@RequestParam(value = "orderId") String orderId ,long userId) {
		return null;
	}



	/**
	 * 用户订单列表
	 */
	@PostMapping("/findOrderByStatus")
	public ResultData findOrderByStatus(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "status", defaultValue = "0") int status, long userId) {
		return orderServiceImpl.findOrderByStatus(pageNo, pageSize, status, userId);
	}

	/**
	 * 根据微信交易编号查询订单详情
	 */
	@GetMapping("/findOrderDetailByOutTradeNo/{outTradeNo}")
	public ResultData quxiaoOrder(@PathVariable String outTradeNo) {
		return orderServiceImpl.findOrderDetailByOutTradeNo(outTradeNo);
	}

	/**
	 * 取消订单
	 */
	@GetMapping("/quxiaoOrder/{orderId}")
	public ResultData quxiaoOrder(@PathVariable long orderId) {
		return orderServiceImpl.quxiaoOrder(orderId);
	}

	/**
	 * 确认收货
	 */
	@GetMapping("/confirmReceipt/{orderId}")
	public ResultData confirmReceipt(@PathVariable long orderId) {
		return orderServiceImpl.confirmReceipt(orderId);
	}

	/**
	 * 付款--->列表上点付款
	 */
	@PostMapping("/secondPay")
	public ResultData secondPay(SecondPay secondPay) {
		return orderServiceImpl.secondPay(secondPay);
	}
	
	/**
	 * 查询不同状态订单的条数
	 */
	@GetMapping("/findOrderNum")
	public ResultData findOrderNum(long userId) {
		return orderServiceImpl.findOrderNum(userId);
	}
	
	/**
	 * 根据订单id查询收货信息
	 */
	@GetMapping("/selectAddressById/{id}")
	public ResultData selectAddressById(@PathVariable long id) {
		return orderServiceImpl.selectAddressById(id);
	}
}
