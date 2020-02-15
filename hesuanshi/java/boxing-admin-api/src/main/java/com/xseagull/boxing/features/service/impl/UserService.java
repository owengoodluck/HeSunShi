package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.User;
import com.xseagull.boxing.features.service.IUserService;

@Service
@Transactional
public class UserService implements IUserService {

	@Autowired
	private UserDao userDao;

	/**
	 * 分页查询用户信息
	 */
	@Override
	public ResultData queryUserByPage(Integer pageNo, Integer pageSize, String nickName) {
		PageHelper.startPage(pageNo, pageSize);
		List<User> list = userDao.queryUserByPage(nickName);
		for (User user : list) {
			if (user.getGender().equals("1")) {
				user.setGender("男");
			} else if (user.getGender().equals("2")) {
				user.setGender("女");
			} else {
				user.setGender("未设置");
			}
		}
		PageInfo<User> page = new PageInfo<User>(list);
		return ResultData.success(page);

	}

	/**
	 * 根据手机号码查询用户
	 */
	@Override
	public ResultData findUserByPhone(String phone) {
		List<User> list = userDao.findUserByPhone(phone);
		return ResultData.success(list);
	}

	/**
	 * 根据用户id查询邀请的用户列表
	 */
	@Override
	public ResultData findUserByRecommenderId(Integer pageNo, Integer pageSize, long uId) {
		List<User> list = userDao.findUserByRecommenderId(uId);
		for (User user : list) {
			if (user.getGender().equals("1")) {
				user.setGender("男");
			} else if (user.getGender().equals("2")) {
				user.setGender("女");
			} else {
				user.setGender("未设置");
			}
		}
		return ResultData.success(list);
	}
}
