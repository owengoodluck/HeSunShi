package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;

public interface IExpressService {

	// 根据订单id查询订单的快递信息
	ResultData findExpressDetail(long orderId);

}
