package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IRefundService;
import com.xseagull.boxing.features.vo.RefundVO;

@RestController
@RequestMapping("/refund")
public class RefundController {
	@Autowired
	private IRefundService refundService;

	/**
	 * 退款列表
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String status, String refundNumber,
			String userName) {
		return refundService.queryByPage(pageNo, pageSize, status, refundNumber, userName);
	}

	/**
	 * 根据退款id查询附件
	 */
	@PostMapping("/findRefundFileById/{id}")
	public ResultData findRefundFileById(@PathVariable String id) {
		return refundService.findRefundFileById(id);
	}
	
	
	/**
	 * 确认退款
	 */
	@PostMapping("/orderRefund")
	public ResultData orderRefund(RefundVO refundVO) {
		return refundService.orderRefund(refundVO);
	}
}
