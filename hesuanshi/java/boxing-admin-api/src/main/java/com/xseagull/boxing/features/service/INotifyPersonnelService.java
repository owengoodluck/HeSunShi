package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.NotifyPersonnel;

/**
 * 通知人员
 */
public interface INotifyPersonnelService {
	// 分页查询通知人员
	public ResultData queryByPage(Integer pageNo, Integer pageSize);

	// 新增通知人员
	public ResultData insert(NotifyPersonnel notifyPersonnel);

	// 删除通知人员
	public ResultData delete(String id);
}
