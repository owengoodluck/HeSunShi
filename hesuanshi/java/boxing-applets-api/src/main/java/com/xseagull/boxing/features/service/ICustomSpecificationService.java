package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CustomSpecification;

public interface ICustomSpecificationService {
	public ResultData insert(CustomSpecification customSpecification);

	// 修改方案
	public ResultData update(CustomSpecification customSpecification);

	// 分页方案
	public ResultData queryByPage(Integer pageNo, Integer pageSize, Integer type, long userId);

	// 删除方案
	public ResultData delete(long id);
}
