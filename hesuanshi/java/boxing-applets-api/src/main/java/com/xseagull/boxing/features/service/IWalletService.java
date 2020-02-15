package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.WalletRecord;

/**
 * 用户钱包管理
 */
public interface IWalletService {

	// 根据用户id查询用户钱包余额
	ResultData findWallet(long userId);

	// 充值
	ResultData recharge(WalletRecord walletRecord);

	// 我的钱包记录
	ResultData findRecord(Integer pageNo, Integer pageSize, long userId);
}