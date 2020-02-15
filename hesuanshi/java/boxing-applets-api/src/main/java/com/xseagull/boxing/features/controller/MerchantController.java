package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.entity.MerchantStaff;
import com.xseagull.boxing.features.service.IMerchantService;
import com.xseagull.boxing.pay.weixin.ServiceNumberLoginCertificate;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

@RestController
@RequestMapping("/merchant")
public class MerchantController {
	@Autowired
	private IMerchantService merchantService;

	/**
	 * 商户入驻申请
	 */
	@PostMapping("/insert")
	public ResultData insert(Merchant merchant) {
		return merchantService.insert(merchant);
	}

	/**
	 * 商户入驻申请判断
	 */
	@GetMapping("/applyJudge/{code}")
	public ResultData applyJudge(@PathVariable String code) {
		ServiceNumberLoginCertificate serviceNumberLoginCertificate = WeiChartUtils
				.getServiceNumberLoginCertificate(code);
		if (serviceNumberLoginCertificate.getErrcode() == 0) {
			ServiceNumberUserInfo serviceNumberUserInfo = WeiChartUtils.getServiceNumberUserInfo(
					serviceNumberLoginCertificate.getAccess_token(), serviceNumberLoginCertificate.getOpenid());
			return merchantService.applyJudge(serviceNumberUserInfo);
		} else {
			return ResultData.warn(ResultCode.INVALID_CODE);
		}
	}

	/**
	 * 查询商户工作台数据
	 */
	@GetMapping("/selectHomeData")
	public ResultData selectHomeData(@RequestParam(value = "userId", defaultValue = "0") long userId) {
		return merchantService.selectHomeData(userId);
	}

	/**
	 * 查询商户信息
	 */
	@GetMapping("/selectMerchantDetail")
	public ResultData selectMerchantDetail(@RequestParam(value = "userId", defaultValue = "0") long userId) {
		return merchantService.selectMerchantDetail(userId);
	}

	/**
	 * 更新商户信息
	 */
	@PostMapping("/update")
	public ResultData update(Merchant merchant) {
		return merchantService.update(merchant);
	}

	/**
	 * 查询商户的店员
	 */
	@PostMapping("/selectMyStaff")
	public ResultData selectMyStaff(@RequestParam(value = "pageNo", defaultValue = "0") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return merchantService.selectMyStaff(pageNo, pageSize, userId);
	}

	/**
	 * 新增店员
	 */
	@PostMapping("/insertStaff")
	public ResultData insertStaff(MerchantStaff merchantStaff, long userId) {
		return merchantService.insertStaff(merchantStaff, userId);
	}

	/**
	 * 删除店员
	 */
	@GetMapping("/deleteStaff/{staffId}")
	public ResultData deleteStaff(@PathVariable long staffId) {
		return merchantService.deleteStaff(staffId);
	}
}
