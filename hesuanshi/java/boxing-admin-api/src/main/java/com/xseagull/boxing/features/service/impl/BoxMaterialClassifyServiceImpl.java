package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxMaterialClassifyDao;
import com.xseagull.boxing.features.entity.BoxMaterialClassify;
import com.xseagull.boxing.features.service.IBoxMaterialClassifyService;

@Service
@Transactional
public class BoxMaterialClassifyServiceImpl implements IBoxMaterialClassifyService {

	@Autowired
	private BoxMaterialClassifyDao boxMaterialClassifyDao;

	// 查询材质用于回显下拉框
	@Override
	public ResultData findClassify() {
		List<BoxMaterialClassify> list = boxMaterialClassifyDao.findClassify();
		return ResultData.success(list);
	}

	// 新增材质分类
	@Override
	public ResultData insert(BoxMaterialClassify boxMaterialClassify) {
		return ResultData.success(boxMaterialClassifyDao.insert(boxMaterialClassify));
	}

	// 分页材质分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxMaterialClassify> list = boxMaterialClassifyDao.queryByPage(name);
		PageInfo<BoxMaterialClassify> page = new PageInfo<BoxMaterialClassify>(list);
		return ResultData.success(page);
	}

	// 更新材质分类
	@Override
	public ResultData update(BoxMaterialClassify boxMaterialClassify) {
		return ResultData.success(boxMaterialClassifyDao.update(boxMaterialClassify));
	}

	// 更新材质分类状态
	@Override
	public ResultData updateStatus(BoxMaterialClassify boxMaterialClassify) {
		return ResultData.success(boxMaterialClassifyDao.update(boxMaterialClassify));
	}

}
