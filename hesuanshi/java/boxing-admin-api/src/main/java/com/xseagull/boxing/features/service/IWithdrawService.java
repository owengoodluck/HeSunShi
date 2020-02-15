package com.xseagull.boxing.features.service;

import javax.servlet.http.HttpServletRequest;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.vo.WithdrawVO;

public interface IWithdrawService {

	// 根据用户id查询提现记录
	ResultData findWithdrawByUserId(Integer pageNo, Integer pageSize, long uId);

	// 查询提现记录列表
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 转账
	ResultData transfer(HttpServletRequest request, WithdrawVO withdrawVO);

}
