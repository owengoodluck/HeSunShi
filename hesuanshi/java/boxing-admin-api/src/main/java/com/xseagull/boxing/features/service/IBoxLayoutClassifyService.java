package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxLayoutClassify;

public interface IBoxLayoutClassifyService {

	// 新增版面分类
	ResultData insert(BoxLayoutClassify boxLayoutClassify);

	// 分页版面分类
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新版面分类
	ResultData update(BoxLayoutClassify boxLayoutClassify);

	// 更新版面分类状态
	ResultData updateStatus(BoxLayoutClassify boxLayoutClassify);

	// 查询版面分类用于下拉框回显
	ResultData findClassify();

}
