package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.WithdrawRecord;

public interface WithdrawRecordDao {

	// 新增一条佣金提现记录
	void insertWithdrawRecord(WithdrawRecord withdrawRecord);

	// 查询提现记录
	List<WithdrawRecord> findTixianRecord(@Param("userId") long userId);

}
