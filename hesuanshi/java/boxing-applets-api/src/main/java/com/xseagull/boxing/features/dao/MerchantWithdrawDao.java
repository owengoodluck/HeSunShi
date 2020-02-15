package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.features.entity.MerchantWithdraw;

public interface MerchantWithdrawDao {

	// 新增一条佣金提现记录
	void insert(MerchantWithdraw merchantWithdraw);

	// 提现记录列表
	List<MerchantWithdraw> findTixianRecord(long merchantId);

}
