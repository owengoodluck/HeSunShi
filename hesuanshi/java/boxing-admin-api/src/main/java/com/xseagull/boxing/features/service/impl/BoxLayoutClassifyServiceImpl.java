package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxLayoutClassifyDao;
import com.xseagull.boxing.features.entity.BoxLayoutClassify;
import com.xseagull.boxing.features.service.IBoxLayoutClassifyService;

@Service
@Transactional
public class BoxLayoutClassifyServiceImpl implements IBoxLayoutClassifyService {

	@Autowired
	private BoxLayoutClassifyDao boxLayoutClassifyDao;

	// 查询版面用于回显下拉框
	@Override
	public ResultData findClassify() {
		List<BoxLayoutClassify> list = boxLayoutClassifyDao.findClassify();
		return ResultData.success(list);
	}

	// 新增版面分类
	@Override
	public ResultData insert(BoxLayoutClassify boxLayoutClassify) {
		return ResultData.success(boxLayoutClassifyDao.insert(boxLayoutClassify));
	}

	// 分页版面分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxLayoutClassify> list = boxLayoutClassifyDao.queryByPage(name);
		PageInfo<BoxLayoutClassify> page = new PageInfo<BoxLayoutClassify>(list);
		return ResultData.success(page);
	}

	// 更新版面分类
	@Override
	public ResultData update(BoxLayoutClassify boxLayoutClassify) {
		return ResultData.success(boxLayoutClassifyDao.update(boxLayoutClassify));
	}

	// 更新版面分类状态
	@Override
	public ResultData updateStatus(BoxLayoutClassify boxLayoutClassify) {
		return ResultData.success(boxLayoutClassifyDao.update(boxLayoutClassify));
	}

}
