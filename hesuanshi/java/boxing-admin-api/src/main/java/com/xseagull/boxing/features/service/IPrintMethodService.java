package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.PrintMethod;

public interface IPrintMethodService {

	// 新增印刷方式
	ResultData insert(PrintMethod printMethod);

	// 分页印刷方式
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新印刷方式
	ResultData update(PrintMethod printMethod);

	// 更新印刷方式状态
	ResultData updateStatus(PrintMethod printMethod);

	// 查询印刷方式用于下拉框回显
	ResultData findPrintMethod();

}
