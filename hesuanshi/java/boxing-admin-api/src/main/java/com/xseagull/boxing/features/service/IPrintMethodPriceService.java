package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.PrintMethodPrice;

public interface IPrintMethodPriceService {

	// 新增印刷方式价格
	ResultData insert(PrintMethodPrice printMethodPrice);

	// 分页印刷方式价格
	ResultData queryByPage(Integer pageNo, Integer pageSize, String printMethodId);

	// 更新印刷方式价格
	ResultData update(PrintMethodPrice printMethodPrice);

	// 删除价格区间
	ResultData delete(long id);

}
