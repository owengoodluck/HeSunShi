package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;

public interface IPhoneCodeService {
	
	public ResultData sendTextCode(String phone);
	
	public ResultData sendMerchantTextCode(String phone);

	public ResultData findLastCode(String phone);
}
