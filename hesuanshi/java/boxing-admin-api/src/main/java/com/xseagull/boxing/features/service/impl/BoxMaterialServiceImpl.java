package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.BoxMaterialDao;
import com.xseagull.boxing.features.entity.BoxMaterial;
import com.xseagull.boxing.features.service.IBoxMaterialService;

@Service
@Transactional
public class BoxMaterialServiceImpl implements IBoxMaterialService {

	@Autowired
	private BoxMaterialDao boxMaterialDao;

	// 新增材质
	@Override
	public ResultData insert(BoxMaterial boxMaterial) {
		return ResultData.success(boxMaterialDao.insert(boxMaterial));
	}

	// 分页材质分类
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<BoxMaterial> list = boxMaterialDao.queryByPage(name);
		PageInfo<BoxMaterial> page = new PageInfo<BoxMaterial>(list);
		return ResultData.success(page);
	}

	// 更新材质
	@Override
	public ResultData update(BoxMaterial boxMaterial) {
		return ResultData.success(boxMaterialDao.update(boxMaterial));
	}

	// 更新材质状态
	@Override
	public ResultData updateStatus(BoxMaterial boxMaterial) {
		return ResultData.success(boxMaterialDao.update(boxMaterial));
	}

	// 删除材质
	@Override
	public ResultData delete(long id) {
		return ResultData.success(boxMaterialDao.delete(id));
	}

}
