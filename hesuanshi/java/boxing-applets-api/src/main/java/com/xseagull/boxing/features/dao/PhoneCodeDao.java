package com.xseagull.boxing.features.dao;

import com.xseagull.boxing.features.entity.PhoneCode;

/**
 * 手机验证码dao
 */
public interface PhoneCodeDao {
	/**
     * 添加手机验证记录
     * @param PhoneCode 手机验证码信息
     * @return int 新增条数
     */
	public int insert(PhoneCode phoneCode);
	
	/**
     * 通过手机号查找最近一条手机验证码信息
     * @param phone 手机号
     * @return PhoneCode 手机验证码信息
     */
	public PhoneCode findLastPhone(String phone);
}
