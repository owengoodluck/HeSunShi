package com.xseagull.boxing.features.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PhoneCodeDao;
import com.xseagull.boxing.features.dao.UserCompanyDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.PhoneCode;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.entity.UserCompany;
import com.xseagull.boxing.features.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private UserCompanyDao userCompanyDao;
	
	@Autowired
	private PhoneCodeDao phoneCodeDao;

	/**
	 * 根据用户id查询用户信息
	 */
	@Override
	public ResultData getUserDetail(String userId) {
		User user = userDao.findUserById(userId);
		return ResultData.success(user);
	}

	/**
	 * 更新用户信息
	 */
	@Override
	public ResultData update(User user, String userId) {
		user.setId(Long.parseLong(userId));
		return ResultData.success(userDao.update(user));
	}

	/**
	 * 绑定手机号
	 */
	@Override
	public ResultData updatPhone(String userId, String phone, String code) {
		PhoneCode phoneCode = phoneCodeDao.findLastPhone(phone);
		if (phoneCode != null) {
			Date date = new Date();
			Date endTime = phoneCode.getEndTime();
			if ((endTime.getTime() + 1000 * 60 * 5) > date.getTime() && code.equals(phoneCode.getCode())) {
				userDao.updatePhone(userId, phone);
				return ResultData.success(null);
			} else {
				return ResultData.warn(ResultCode.VERIFY_CODE_ERROR);
			}
		} else {
			return ResultData.warn(ResultCode.VERIFY_CODE_ERROR);
		}
	}

	/**
	 * 根据用户id查询用户企业信息
	 */
	@Override
	public ResultData getUserCompanyDetail(long userId) {
		return ResultData.success(userCompanyDao.getUserCompanyDetail(userId));
	}

	/**
	 * 保存用户企业信息
	 */
	@Override
	public ResultData saveUserCompany(UserCompany userCompany) {
		if (userCompany.getId() == 0) {
			userCompanyDao.insert(userCompany);
		} else {
			userCompanyDao.update(userCompany);
		}
		return ResultData.success(null);
	}

	/**
	 * 我邀请的用户列表
	 */
	@Override
	public ResultData findMyUser(Integer pageNo, Integer pageSize, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userDao.findMyUser(userId);
		PageInfo<User> page = new PageInfo<User>(list);
		return ResultData.success(page);
	}
}
