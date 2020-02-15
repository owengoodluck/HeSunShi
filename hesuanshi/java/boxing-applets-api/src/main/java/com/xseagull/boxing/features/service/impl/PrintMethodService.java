package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PrintMethodDao;
import com.xseagull.boxing.features.service.IPrintMethodService;

@Service
@Transactional
public class PrintMethodService implements IPrintMethodService {

	@Autowired
	private PrintMethodDao printMethodDao;

	/**
	 * 查询列表
	 */
	@Override
	public ResultData queryList() {
		return ResultData.success(printMethodDao.queryList());
	}
}
