package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxLayout;

public interface IBoxLayoutService {

	// 新增版面
	ResultData insert(BoxLayout boxLayout);

	// 分页版面
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新版面
	ResultData update(BoxLayout boxLayout);

	// 更新版面状态
	ResultData updateStatus(BoxLayout boxLayout);

	// 删除版面
	ResultData delete(long id);

}
