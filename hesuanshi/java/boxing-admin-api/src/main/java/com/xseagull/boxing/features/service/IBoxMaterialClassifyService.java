package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxMaterialClassify;

public interface IBoxMaterialClassifyService {

	// 新增材质分类
	ResultData insert(BoxMaterialClassify boxMaterialClassify);

	// 分页材质分类
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新材质分类
	ResultData update(BoxMaterialClassify boxMaterialClassify);

	// 更新材质分类状态
	ResultData updateStatus(BoxMaterialClassify boxMaterialClassify);

	// 查询材质分类用于下拉框回显
	ResultData findClassify();

}
