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
import com.xseagull.boxing.features.service.IMerchantOrderService;

@RestController
@RequestMapping("/merchantOrder")
public class MerchantOrderController {

	@Autowired
	private IMerchantOrderService merchantOrderServiceImpl;

	/**
	 * 商户订单列表
	 */
	@PostMapping("/selectMerchantOrderByStatus")
	public ResultData selectMerchantOrderByStatus(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "merchantOrderStatus", defaultValue = "0") int merchantOrderStatus,
			@RequestParam(value = "userOrderStatus", defaultValue = "0") int userOrderStatus, long userId) {
		return merchantOrderServiceImpl.selectMerchantOrderByStatus(pageNo, pageSize, merchantOrderStatus,
				userOrderStatus, userId);
	}

	/**
	 * 根据商户订单id查询订单详情
	 */
	@GetMapping("/findOrderDetailByMerchantOrderId/{merchantOrderId}")
	public ResultData findOrderDetailByMerchantOrderId(@PathVariable long merchantOrderId) {
		return merchantOrderServiceImpl.findOrderDetailByMerchantOrderId(merchantOrderId);
	}

	/**
	 * 确认接单
	 */
	@GetMapping("/confirm/{id}")
	public ResultData confirm(@PathVariable long id, long userId) {
		return merchantOrderServiceImpl.confirm(id, userId);
	}

	/**
	 * 驳回订单
	 */
	@PostMapping("/reject")
	public ResultData reject(long id, String reason) {
		return merchantOrderServiceImpl.reject(id, reason);
	}

	/**
	 * 备注订单
	 */
	@PostMapping("/remark")
	public ResultData remark(long id, String remark) {
		return merchantOrderServiceImpl.remark(id, remark);
	}

	/**
	 * 查询快递列表
	 */
	@GetMapping("/getExpress")
	public ResultData getExpress() {
		return merchantOrderServiceImpl.getExpress();
	}

	/**
	 * 发货
	 */
	@PostMapping("/orderSend")
	public ResultData orderSend(OrderExpress orderExpress) {
		return merchantOrderServiceImpl.orderSend(orderExpress);
	}

	/**
	 * 查询商户结算页面数据
	 */
	@GetMapping("/selectSettlementData")
	public ResultData selectSettlementData(@RequestParam(value = "userId", defaultValue = "0") long userId) {
		return merchantOrderServiceImpl.selectSettlementData(userId);
	}

	/**
	 * 全部提交提现
	 */
	@PostMapping("/withdrawableAll")
	public ResultData withdrawableAll(String money, long userId) {
		return merchantOrderServiceImpl.withdrawableAll(money, userId);
	}
	
	/**
	 * 提现记录列表
	 */
	@PostMapping("/findTixianRecord")
	public ResultData findTixianRecord(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return merchantOrderServiceImpl.findTixianRecord(pageNo, pageSize, userId);
	}
	
	/**
	 * 佣金记录列表
	 */
	@PostMapping("/findCommissionRecord")
	public ResultData findCommissionRecord(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return merchantOrderServiceImpl.findCommissionRecord(pageNo, pageSize, userId);
	}
}
