package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxMaterialDao;
import com.xseagull.boxing.features.service.IBoxMaterialService;

@Service
@Transactional
public class BoxMaterialService implements IBoxMaterialService {

	@Autowired
	private BoxMaterialDao boxMaterialDao;

	/**
	 * 查询分类
	 */
	@Override
	public ResultData queryClassify(long boxTypeDetailId) {
		return ResultData.success(boxMaterialDao.queryClassify(boxTypeDetailId));
	}
	
	/**
	 * 查询分类详情
	 */
	@Override
	public ResultData queryClassifyDetailById(long id) {
		return ResultData.success(boxMaterialDao.queryClassifyDetailById(id));
	}

	
	@Override
	public ResultData findClassifyDetailNumByTId(long tId) {
		return ResultData.success(boxMaterialDao.findClassifyDetailNumByTId(tId));
	}

	@Override
	public ResultData queryClassifyDetailByTId(long tId) {
		return ResultData.success(boxMaterialDao.queryClassifyDetailByTId(tId));
	}
}
