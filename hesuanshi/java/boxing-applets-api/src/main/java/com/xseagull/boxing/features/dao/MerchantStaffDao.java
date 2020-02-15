package com.xseagull.boxing.features.dao;

import java.util.List;

import com.xseagull.boxing.features.entity.MerchantStaff;

public interface MerchantStaffDao {
	public int insert(MerchantStaff merchantStaff);
	
	public int update(MerchantStaff merchantStaff);
	
	public MerchantStaff selectByPhone(String phone);
	
	public List<MerchantStaff> selectStaffByMerchantId(long userId);

	public int delete(long staffId);
}
