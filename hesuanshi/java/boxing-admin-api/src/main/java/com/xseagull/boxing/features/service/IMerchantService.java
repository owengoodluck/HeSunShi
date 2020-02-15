package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.vo.MerchantOrderVO;

/**
 * 商户人员
 */
public interface IMerchantService {
	// 分页查询商户
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 审核商户人员
	public ResultData review(Merchant merchant);

	// 商户佣金记录列表
	public ResultData findCommissionByMerchantId(long merchantId);

	// 查询店员
	public ResultData findStaffById(long id);

	// 商户提现记录列表
	public ResultData findWithdrawByMerchantId(long merchantId);

	// 查询所有用户提现记录
	public ResultData queryWithdrawByPage(Integer pageNo, Integer pageSize, String name, Integer status);

	// 处理提现
	public ResultData transfer(long id);

	// 根据订单ID查询派单情况
	public ResultData findMerchantOrderByOrderId(long orderId);

	// 查询所有通过审核的商户
	public ResultData getMerchant();

	// 派单
	public ResultData sendOrder(MerchantOrderVO merchantOrderVO);

}
