package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.MerchantCommission;

public interface MerchantCommissionDao {
	public int insert(MerchantCommission merchantCommission);

	// 提现后修改记录状态和提现ID
	public void updateStatusAndWithdrawRecordId(@Param("merchantWithdrawId") long merchantWithdrawId,
			@Param("merchantId") long merchantId);

	// 查询佣金记录
	public List<MerchantCommission> findCommissionRecord(long merchantId);
}
