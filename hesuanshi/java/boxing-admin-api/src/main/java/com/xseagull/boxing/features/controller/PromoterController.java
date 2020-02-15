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
	 * 分页查询推广人员
	 * 
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@GetMapping("/queryByPage")
	public ResultData queryByPage(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
			@RequestParam(value = "name") String name) {
		return promoterService.queryByPage(pageNo, pageSize, name);
	}

	/**
	 * 修改佣金比例
	 *
	 * @param notifyPersonnel
	 * @return
	 */
	@PostMapping("/updateRate")
	public ResultData updateRate(Promoter promoter) {
		return promoterService.updateRate(promoter);
	}

	/**
	 * 审核推广人员
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping("/review")
	public ResultData review(Promoter promoter) {
		return promoterService.review(promoter);
	}

	/**
	 * 用户佣金记录列表
	 */
	@PostMapping("/findCommissionRecordByUserId")
	public ResultData findCommissionRecordByUserId(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long uId) {
		return promoterService.findCommissionRecordByUserId(pageNo, pageSize, uId);
	}
}
