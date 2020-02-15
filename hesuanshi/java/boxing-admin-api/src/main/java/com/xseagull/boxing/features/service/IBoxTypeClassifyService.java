package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxTypeClassify;

public interface IBoxTypeClassifyService {

	// 新增盒型分类
	ResultData insert(BoxTypeClassify boxTypeClassify);

	// 分页盒型分类
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新盒型分类
	ResultData update(BoxTypeClassify boxTypeClassify);

	// 更新盒型分类状态
	ResultData updateStatus(BoxTypeClassify boxTypeClassify);

	// 查询盒型分类用于下拉框回显
	ResultData findClassify();

}
