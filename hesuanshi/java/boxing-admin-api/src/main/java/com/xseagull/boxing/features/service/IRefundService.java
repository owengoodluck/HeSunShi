package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.vo.RefundVO;

public interface IRefundService {

	ResultData queryByPage(Integer pageNo, Integer pageSize, String status, String refundNumber, String userName);

	ResultData findRefundFileById(String id);

	ResultData orderRefund(RefundVO refundVO);

}
