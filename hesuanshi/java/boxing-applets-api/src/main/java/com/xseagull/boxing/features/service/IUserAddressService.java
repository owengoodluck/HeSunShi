package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.vo.UserAddressVO;

public interface IUserAddressService {

	// 查询用户收货地址列表
	ResultData findAddressList(long userId);

	// 设为默认地址
	ResultData setMoren(long id, long userId);

	// 删除地址
	ResultData delAddress(long id);

	// 保存地址
	ResultData saveAddress(UserAddressVO userAddressVO);

	// 获取用户默认地址
	ResultData findMorenAddress(long userId);

}
