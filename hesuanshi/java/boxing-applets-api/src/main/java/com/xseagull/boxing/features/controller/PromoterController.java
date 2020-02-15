package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Promoter;
import com.xseagull.boxing.features.service.IPromoterService;

@RestController
@RequestMapping("/promoter")
public class PromoterController {
	@Autowired
	private IPromoterService promoterService;

	/**
	 * 保存
	 */
	@PostMapping("/save")
	public ResultData save(Promoter promoter) {
		return promoterService.save(promoter);
	}

	/**
	 * 根据用户id查询是不是推广人员
	 */
	@GetMapping("/findPromoterByUserId")
	public ResultData findPromoterByUserId(long userId) {
		return promoterService.findPromoterByUserId(userId);
	}
	
	/**
	 * 根据用户id查询是不是推广人员
	 */
	@GetMapping("/getPromoterByUserId")
	public ResultData getPromoterByUserId(long userId) {
		return promoterService.getPromoterByUserId(userId);
	}

	/**
	 * 增加邀请次数
	 */
	@GetMapping("/addNumber")
	public ResultData addNumber(long userId) {
		return promoterService.addNumber(userId);
	}

	/**
	 * 查询佣金数据
	 */
	@GetMapping("/findCommissionData")
	public ResultData findCommissionData(long userId) {
		return promoterService.findCommissionData(userId);
	}

	/**
	 * 全部提交提现
	 */
	@PostMapping("/withdrawableAll")
	public ResultData withdrawableAll(String withdrawableMoney, String formId, long userId) {
		return promoterService.withdrawableAll(withdrawableMoney, formId, userId);
	}

	/**
	 * 提现记录列表
	 */
	@PostMapping("/findTixianRecord")
	public ResultData findTixianRecord(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return promoterService.findTixianRecord(pageNo, pageSize, userId);
	}

	/**
	 * 佣金记录列表
	 */
	@PostMapping("/findCommissionRecord")
	public ResultData findCommissionRecord(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return promoterService.findCommissionRecord(pageNo, pageSize, userId);
	}
}
