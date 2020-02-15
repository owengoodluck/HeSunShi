package com.xseagull.boxing.features.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xseagull.boxing.features.entity.NotifyPersonnel;

/**
 * 通知人员
 */
public interface NotifyPersonnelDao {
	/**
	 * 分页查询通知人员
	 * 
	 * @param nickName
	 * @return
	 */
	public List<NotifyPersonnel> queryByPage();

	/**
	 * 新增通知人员
	 * 
	 * @param notifyPersonnel
	 */
	public void insert(NotifyPersonnel notifyPersonnel);

	/**
	 * 删除通知人员
	 * 
	 * @param id
	 */
	public void delete(@Param("id") String id);

}
