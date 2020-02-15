package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.ResourceFile;

public interface IResourceService {

	ResultData delete(ResourceFile resourceFile);

	// 根据编号查询资源列表
	ResultData findResourceByNumber(String number);

}
