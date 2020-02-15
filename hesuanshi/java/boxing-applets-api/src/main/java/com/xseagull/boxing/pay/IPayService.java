package com.xseagull.boxing.pay;

import com.xseagull.boxing.common.ResultData;

public interface IPayService {

	/**
	 * 订单支付同步通知
	 * 
	 * @param outTradeNo
	 *            订单号
	 * @param ResultData
	 *            返回信息
	 */
	public ResultData orderNotify(String outTradeNo);

}
