package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.CommissionRecord;
import com.xseagull.boxing.features.vo.CommissionDataVO;

/**
 * 佣金记录管理
 */
public interface CommissionRecordDao {

	// 添加佣金记录
	public void insert(CommissionRecord commissionRecord);

	// 查询佣金数据
	public CommissionDataVO findCommissionData(@Param("userId") long userId);

	// 提现后修改记录状态和提现ID
	public void updateStatusAndWithdrawRecordId(@Param("withdrawRecordId") long withdrawRecordId,
			@Param("userId") long userId);

	// 佣金记录列表
	public List<CommissionRecord> findCommissionRecord(@Param("userId") long userId);

}
