package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.CustomSpecification;

public interface ICustomSpecificationService {

	// 分页方案
	public ResultData queryByPage(Integer pageNo, Integer pageSize, Integer type);

	// 方案报价
	public ResultData quotedPrice(CustomSpecification customSpecification);

	// 获取方案
	public ResultData queryByCustomNumber(String customNumber);

	/**
	 * 执行更新为已上传设计稿
	 * @param customNumber
	 * @return
	 */
	public ResultData isUpload(String customNumber);

}
