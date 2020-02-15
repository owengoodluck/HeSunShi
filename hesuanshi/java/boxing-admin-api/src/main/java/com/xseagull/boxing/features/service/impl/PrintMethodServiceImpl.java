package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PrintMethodDao;
import com.xseagull.boxing.features.entity.PrintMethod;
import com.xseagull.boxing.features.service.IPrintMethodService;

@Service
@Transactional
public class PrintMethodServiceImpl implements IPrintMethodService {

	@Autowired
	private PrintMethodDao printMethodDao;

	// 查询印刷方式用于回显下拉框
	@Override
	public ResultData findPrintMethod() {
		List<PrintMethod> list = printMethodDao.findPrintMethod();
		return ResultData.success(list);
	}

	// 新增印刷方式
	@Override
	public ResultData insert(PrintMethod printMethod) {
		return ResultData.success(printMethodDao.insert(printMethod));
	}

	// 分页印刷方式
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String name) {
		PageHelper.startPage(pageNo, pageSize);
		List<PrintMethod> list = printMethodDao.queryByPage(name);
		PageInfo<PrintMethod> page = new PageInfo<PrintMethod>(list);
		return ResultData.success(page);
	}

	// 更新印刷方式
	@Override
	public ResultData update(PrintMethod printMethod) {
		return ResultData.success(printMethodDao.update(printMethod));
	}

	// 更新印刷方式状态
	@Override
	public ResultData updateStatus(PrintMethod printMethod) {
		return ResultData.success(printMethodDao.update(printMethod));
	}

}
