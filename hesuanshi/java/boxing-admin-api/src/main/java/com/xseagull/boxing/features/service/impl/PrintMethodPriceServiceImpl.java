package com.xseagull.boxing.features.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.PrintMethodPriceDao;
import com.xseagull.boxing.features.entity.PrintMethodPrice;
import com.xseagull.boxing.features.service.IPrintMethodPriceService;

@Service
@Transactional
public class PrintMethodPriceServiceImpl implements IPrintMethodPriceService {

	@Autowired
	private PrintMethodPriceDao printMethodPriceDao;

	// 新增印刷方式价格
	@Override
	public ResultData insert(PrintMethodPrice printMethodPrice) {
		return ResultData.success(printMethodPriceDao.insert(printMethodPrice));
	}

	// 分页印刷方式价格
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String printMethodId) {
		PageHelper.startPage(pageNo, pageSize);
		List<PrintMethodPrice> list = printMethodPriceDao.queryByPage(printMethodId);
		PageInfo<PrintMethodPrice> page = new PageInfo<PrintMethodPrice>(list);
		return ResultData.success(page);
	}

	// 更新印刷方式价格
	@Override
	public ResultData update(PrintMethodPrice printMethodPrice) {
		return ResultData.success(printMethodPriceDao.update(printMethodPrice));
	}

	//删除价格区间
	@Override
	public ResultData delete(long id) {
		return ResultData.success(printMethodPriceDao.delete(id));
	}

}
