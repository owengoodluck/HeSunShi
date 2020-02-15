package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.UserAddressDao;
import com.xseagull.boxing.features.service.IUserAddressService;
import com.xseagull.boxing.features.vo.UserAddressVO;

@Service
public class UserAddressServiceImpl implements IUserAddressService {

	@Autowired
	private UserAddressDao userAddressDao;

	/**
	 * 查询用户收货地址列表
	 */
	@Override
	public ResultData findAddressList(long userId) {
		return ResultData.success(userAddressDao.findAddressListByUserId(userId));
	}

	/**
	 * 设为默认地址
	 */
	@Override
	public ResultData setMoren(long id, long userId) {
		return ResultData.success(userAddressDao.setMoren(id, userId));
	}

	/**
	 * 删除地址
	 */
	@Override
	public ResultData delAddress(long id) {
		return ResultData.success(userAddressDao.delAddress(id));
	}

	/**
	 * 保存地址
	 */
	@Override
	public ResultData saveAddress(UserAddressVO userAddressVO) {
		if(userAddressVO.getStatus() == 1){
			//把这个用户下的所有的地址都改为不是默认
			userAddressDao.updateStatus(userAddressVO.getUserId(),0);
		}
		if (userAddressVO.getId() == 0) {
			userAddressDao.insertUserAddress(userAddressVO);
		} else {
			userAddressDao.updateUserAddress(userAddressVO);
		}
		return ResultData.success(null);
	}

	/**
	 * 获取用户默认地址
	 */
	@Override
	public ResultData findMorenAddress(long userId) {
		return ResultData.success(userAddressDao.findMorenAddress(userId));
	}
}
