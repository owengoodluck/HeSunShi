package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.NotifyPersonnelDao;
import com.xseagull.boxing.features.entity.NotifyPersonnel;
import com.xseagull.boxing.features.service.INotifyPersonnelService;

@Service
@Transactional
public class NotifyPersonnelService implements INotifyPersonnelService {

	@Autowired
	private NotifyPersonnelDao notifyPersonnelDao;

	/**
	 * 分页查询通知人员
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<NotifyPersonnel> list = notifyPersonnelDao.queryByPage();
		PageInfo<NotifyPersonnel> page = new PageInfo<NotifyPersonnel>(list);
		return ResultData.success(page);

	}

	/**
	 * 新增通知人员
	 */
	@Override
	public ResultData insert(NotifyPersonnel notifyPersonnel) {
		try {
			notifyPersonnelDao.insert(notifyPersonnel);
			return ResultData.success(null);
		} catch (Exception e) {
			return ResultData.warn(ResultCode.REGISTER_ERROR, "改用户已经是下单通知人员");
		}
	}

	/**
	 * 删除通知人员
	 */
	@Override
	public ResultData delete(String id) {
		notifyPersonnelDao.delete(id);
		return ResultData.success(null);
	}
}
