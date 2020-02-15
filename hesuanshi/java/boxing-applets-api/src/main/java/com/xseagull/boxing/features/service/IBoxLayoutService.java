package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;

public interface IBoxLayoutService {
	public ResultData queryClassify(long boxTypeDetailId);
	
	public ResultData queryClassifyDetailById(long id);
	
	public ResultData queryClassifyDetailByTId(long tId);
}
