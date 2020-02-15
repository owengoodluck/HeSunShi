package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IExpressService;

@RestController
@RequestMapping("/express")
public class ExpressController {

	@Autowired
	private IExpressService expressService;

	/**
	 * 根据订单id查询订单的快递信息
	 */
	@GetMapping("/findExpressDetail/{orderId}")
	public ResultData findExpressDetail(@PathVariable long orderId) {
		return expressService.findExpressDetail(orderId);
	}

	
}
