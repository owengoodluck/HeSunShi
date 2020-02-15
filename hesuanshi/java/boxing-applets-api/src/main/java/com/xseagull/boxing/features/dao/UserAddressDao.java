package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.vo.UserAddressVO;

/**
 * 地址管理
 */
public interface UserAddressDao {

	// 查询用户收货地址列表
	List<UserAddressVO> findAddressListByUserId(@Param("userId") long userId);

	// 设为默认地址
	public int setMoren(@Param("id") long id, @Param("userId") long userId);

	// 删除地址
	public int delAddress(@Param("id") long id);

	// 新增地址
	public void insertUserAddress(UserAddressVO userAddressVO);

	// 修改地址
	public void updateUserAddress(UserAddressVO userAddressVO);

	// 获取用户默认地址
	UserAddressVO findMorenAddress(@Param("userId") long userId);

	// 根据id修改用户地址的状态
	public int updateStatus(@Param("userId") long userId, @Param("status") int status);

}
