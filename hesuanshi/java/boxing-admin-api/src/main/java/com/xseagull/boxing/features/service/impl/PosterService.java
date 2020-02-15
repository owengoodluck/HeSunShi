package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PosterDao;
import com.xseagull.boxing.features.entity.Poster;
import com.xseagull.boxing.features.service.IPosterService;

@Service
@Transactional
public class PosterService implements IPosterService {

	@Autowired
	private PosterDao posterDao;

	/**
	 * 分页查询推广海报
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize) {
		PageHelper.startPage(pageNo, pageSize);
		List<Poster> list = posterDao.queryByPage();
		PageInfo<Poster> page = new PageInfo<Poster>(list);
		return ResultData.success(page);

	}

	/**
	 * 新增海报
	 */
	@Override
	public ResultData insert(Poster poster) {
		return ResultData.success(posterDao.insert(poster));
	}

	/**
	 * 删除海报
	 */
	@Override
	public ResultData delete(String id) {
		return ResultData.success(posterDao.delete(id));
	}

}
