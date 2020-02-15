package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CommissionRecord;

/**
 * 佣金记录管理
 */
public interface CommissionRecordDao {

	// 佣金记录列表
	public List<CommissionRecord> findCommissionRecordByUserId(@Param("uId") long uId);

	//修改佣金记录状态
	public void updateStatus(@Param("withdrawRecordId") long withdrawRecordId, @Param("status") int status);

}
