package com.xseagull.boxing.features.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.ExpressDao;
import com.xseagull.boxing.features.service.IExpressService;

@Service
@Transactional
public class ExpressService implements IExpressService {

	@Autowired
	private ExpressDao expressDao;

	/**
	 * 根据订单id查询订单的快递信息
	 */
	@Override
	public ResultData findExpressDetail(long orderId) {
		return ResultData.success(expressDao.findExpressDetail(orderId));
	}

}
