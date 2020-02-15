package com.xseagull.boxing.features.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.WalletRecord;
import com.xseagull.boxing.features.service.IWalletService;

@RestController
@RequestMapping("/wallet")
public class WalletController {
	@Autowired
	private IWalletService walletService;

	/**
	 * 根据用户id查询用户钱包余额
	 */
	@GetMapping("/findWallet")
	public ResultData findWallet(long userId) {
		return walletService.findWallet(userId);
	}
	
	/**
	 * 充值
	 */
	@PostMapping("/recharge")
	public ResultData recharge(WalletRecord walletRecord) {
		return walletService.recharge(walletRecord);
	}

	/**
	 * 我的钱包记录
	 */
	@PostMapping("/findRecord")
	public ResultData findRecord(@RequestParam(value = "pageNo", defaultValue = "1") Integer pageNo,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, long userId) {
		return walletService.findRecord(pageNo, pageSize, userId);
	}
}
