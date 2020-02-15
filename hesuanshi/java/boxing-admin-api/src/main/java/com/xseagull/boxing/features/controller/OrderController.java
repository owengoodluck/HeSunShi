package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.OrderExpress;
import com.xseagull.boxing.features.service.IOrderService;
import com.xseagull.boxing.features.vo.OrderDetailVO;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService orderService;

	/**
	 * 订单分页
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String status,
			String orderNumber) {
		return orderService.queryByPage(pageNo, pageSize, status, orderNumber);
	}

	/**
	 * 根据订单id查询订单详情
	 */
	@PostMapping("/findOrderDetailByOrderId/{orderId}")
	public ResultData findOrderDetailByOrderId(@PathVariable String orderId) {
		return orderService.findOrderDetailByOrderId(orderId);
	}

	/**
	 * 查询快递列表
	 */
	@GetMapping("/getExpress")
	public ResultData getExpress() {
		return orderService.getExpress();
	}

	/**
	 * 发货
	 */
	@PostMapping("/orderSend")
	public ResultData orderSend(OrderExpress orderExpress) {
		return orderService.orderSend(orderExpress);
	}

	/**
	 * 根据订单详情id查询订单详情
	 */
	@PostMapping("/findOrderDetailByDetailId/{orderDetailId}")
	public ResultData findOrderDetailByDetailId(@PathVariable String orderDetailId) {
		return orderService.findOrderDetailByDetailId(orderDetailId);
	}

	/**
	 * 改价
	 */
	@PostMapping("/updatePrice")
	public ResultData updatePrice(OrderDetailVO orderDetailVO) {
		return orderService.updatePrice(orderDetailVO);
	}

	/**
	 * 订单分页(根据派单情况查询)
	 */
	@GetMapping("/queryByPageAndMerchantStatus")
	public ResultData queryByPageAndMerchantStatus(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String status,
			String merchantStatus, String orderNumber) {
		return orderService.queryByPageAndMerchantStatus(pageNo, pageSize, status, merchantStatus, orderNumber);
	}
}
