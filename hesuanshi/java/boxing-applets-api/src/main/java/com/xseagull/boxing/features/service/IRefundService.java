package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Refund;

public interface IRefundService {

	// 提交退款
	ResultData refundSub(Refund refund);

	// 查询用户退款列表
	ResultData findRefundList(Integer pageNo, Integer pageSize, long userId);

}
