package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.Calculated;

public interface IBoxTypeService {
	public ResultData queryClassify();
	
	public ResultData queryClassify5();
	
	public ResultData queryClassifyDetailById(long id);
	
	public ResultData calculated(Calculated calculated);

	public ResultData queryClassifyDetailAll();

	public ResultData getAdvancePaymentPrice();
}
