package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PosterDao;
import com.xseagull.boxing.features.service.IPosterService;

@Service
@Transactional
public class PosterService implements IPosterService {

	@Autowired
	private PosterDao posterDao;

	@Override
	public ResultData queryList() {
		return ResultData.success(posterDao.queryList());
	}
}
