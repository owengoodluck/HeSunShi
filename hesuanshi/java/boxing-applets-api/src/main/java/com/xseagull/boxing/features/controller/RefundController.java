package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Refund;
import com.xseagull.boxing.features.service.IRefundService;

@RestController
@RequestMapping("/refund")
public class RefundController {

	@Autowired
	private IRefundService refundService;

	/**
	 * 提交退款
	 */
	@PostMapping("/refundSub")
	public ResultData refundSub(Refund refund) {
		return refundService.refundSub(refund);
	}

	/**
	 * 用户退款列表
	 */
	@PostMapping("/findRefundList")
	public ResultData findRefundList(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return refundService.findRefundList(pageNo, pageSize, userId);
	}
}
