package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Promoter;

/**
 * 推广人员
 */
public interface IPromoterService {
	// 分页查询推广人员
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 修改佣金比例
	public ResultData updateRate(Promoter promoter);

	// 审核推广人员
	public ResultData review(Promoter promoter);

	// 用户佣金记录列表
	public ResultData findCommissionRecordByUserId(Integer pageNo, Integer pageSize, long uId);

}
