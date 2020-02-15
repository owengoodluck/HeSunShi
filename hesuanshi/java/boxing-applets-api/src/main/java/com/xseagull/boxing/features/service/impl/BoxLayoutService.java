package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxLayoutDao;
import com.xseagull.boxing.features.service.IBoxLayoutService;

@Service
@Transactional
public class BoxLayoutService implements IBoxLayoutService {

	@Autowired
	private BoxLayoutDao boxLayoutDao;

	/**
	 * 查询分类
	 */
	@Override
	public ResultData queryClassify(long boxTypeDetailId) {
		return ResultData.success(boxLayoutDao.queryClassify(boxTypeDetailId));
	}
	
	/**
	 * 查询分类详情
	 */
	@Override
	public ResultData queryClassifyDetailById(long id) {
		return ResultData.success(boxLayoutDao.queryClassifyDetailById(id));
	}
	
	/**
	 * 查询分类详情
	 */
	@Override
	public ResultData queryClassifyDetailByTId(long tId) {
		return ResultData.success(boxLayoutDao.queryClassifyDetailByTId(tId));
	}
	
	
}
