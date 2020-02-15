package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxTypeClassifyDao;
import com.xseagull.boxing.features.entity.BoxTypeClassify;
import com.xseagull.boxing.features.service.IBoxTypeClassifyService;

@Service
@Transactional
public class BoxTypeClassifyServiceImpl implements IBoxTypeClassifyService {

	@Autowired
	private BoxTypeClassifyDao boxTypeClassifyDao;


	// 查询部门用于回显下拉框
	@Override
	public ResultData findClassify() {
		List<BoxTypeClassify> list = boxTypeClassifyDao.findClassify();
		return ResultData.success(list);
	}

	// 新增盒型分类
	@Override
	public ResultData insert(BoxTypeClassify boxTypeClassify) {
		return ResultData.success(boxTypeClassifyDao.insert(boxTypeClassify));
	}

	// 分页盒型分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxTypeClassify> list = boxTypeClassifyDao.queryByPage(name);
		PageInfo<BoxTypeClassify> page = new PageInfo<BoxTypeClassify>(list);
		return ResultData.success(page);
	}

	// 更新盒型分类
	@Override
	public ResultData update(BoxTypeClassify boxTypeClassify) {
		return ResultData.success(boxTypeClassifyDao.update(boxTypeClassify));
	}

	//更新盒型分类状态
	@Override
	public ResultData updateStatus(BoxTypeClassify boxTypeClassify) {
		return ResultData.success(boxTypeClassifyDao.update(boxTypeClassify));
	}

}
