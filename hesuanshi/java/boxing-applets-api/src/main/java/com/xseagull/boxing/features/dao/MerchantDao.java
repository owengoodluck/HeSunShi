package com.xseagull.boxing.features.dao;

import com.xseagull.boxing.features.entity.Merchant;
import com.xseagull.boxing.features.vo.HomeDataVO;

public interface MerchantDao {
	public int insert(Merchant merchant);

	public Merchant selectById(long id);

	public Merchant selectByPhone(String phone);

	public Merchant selectByUnionid(String unionid);

	public int update(Merchant merchant);

	public HomeDataVO selectHomeData(long userId);
}
