package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxLayoutDao;
import com.xseagull.boxing.features.entity.BoxLayout;
import com.xseagull.boxing.features.service.IBoxLayoutService;

@Service
@Transactional
public class BoxLayoutServiceImpl implements IBoxLayoutService {

	@Autowired
	private BoxLayoutDao boxLayoutDao;

	// 新增版面
	@Override
	public ResultData insert(BoxLayout boxLayout) {
		return ResultData.success(boxLayoutDao.insert(boxLayout));
	}

	// 分页版面分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxLayout> list = boxLayoutDao.queryByPage(name);
		PageInfo<BoxLayout> page = new PageInfo<BoxLayout>(list);
		return ResultData.success(page);
	}

	// 更新版面
	@Override
	public ResultData update(BoxLayout boxLayout) {
		return ResultData.success(boxLayoutDao.update(boxLayout));
	}

	// 更新版面状态
	@Override
	public ResultData updateStatus(BoxLayout boxLayout) {
		return ResultData.success(boxLayoutDao.update(boxLayout));
	}

	// 删除材质
	@Override
	public ResultData delete(long id) {
		return ResultData.success(boxLayoutDao.delete(id));
	}

}
