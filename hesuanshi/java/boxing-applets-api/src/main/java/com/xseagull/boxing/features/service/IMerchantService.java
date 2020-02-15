package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.entity.MerchantStaff;
import com.xseagull.boxing.pay.weixin.ServiceNumberUserInfo;

public interface IMerchantService {
	public ResultData insert(Merchant merchant);

	public ResultData applyJudge(ServiceNumberUserInfo serviceNumberUserInfo);

	public ResultData selectHomeData(long userId);

	public ResultData selectMerchantDetail(long userId);

	public ResultData update(Merchant merchant);

	public ResultData selectMyStaff(Integer pageNo, Integer pageSize, long userId);

	public ResultData insertStaff(MerchantStaff merchantStaff, long userId);

	public ResultData deleteStaff(long staffId);
}
