package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Promoter;

/**
 * 推广人员管理
 */
public interface IPromoterService {

	// 保存
	public ResultData save(Promoter promoter);

	// 根据用户id查询是不是推广人员
	public ResultData findPromoterByUserId(long userId);
	
	public ResultData getPromoterByUserId(long userId);

	// 增加邀请次数
	public ResultData addNumber(long userId);

	// 查询佣金数据
	public ResultData findCommissionData(long userId);

	// 提现
	public ResultData withdrawableAll(String withdrawableMoney, String formId, long userId);

	// 提现记录列表
	public ResultData findTixianRecord(Integer pageNo, Integer pageSize, long userId);

	// 佣金记录列表
	public ResultData findCommissionRecord(Integer pageNo, Integer pageSize, long userId);
}
