package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;

public interface IBoxMaterialService {
	public ResultData queryClassify(long boxTypeDetailId);
	
	public ResultData queryClassifyDetailById(long id);

	public ResultData findClassifyDetailNumByTId(long tId);

	public ResultData queryClassifyDetailByTId(long tId);
}
