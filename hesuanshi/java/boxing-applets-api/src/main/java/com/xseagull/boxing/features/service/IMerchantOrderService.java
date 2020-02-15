package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.OrderExpress;

public interface IMerchantOrderService {

	ResultData selectMerchantOrderByStatus(Integer pageNo, Integer pageSize, int merchantOrderStatus,
			int userOrderStatus, long userId);

	ResultData findOrderDetailByMerchantOrderId(long merchantOrderId);

	ResultData confirm(long id, long userId);

	ResultData reject(long id, String reason);

	ResultData remark(long id, String remark);

	ResultData getExpress();

	ResultData orderSend(OrderExpress orderExpress);

	ResultData selectSettlementData(long userId);

	ResultData withdrawableAll(String money, long userId);

	ResultData findTixianRecord(Integer pageNo, Integer pageSize, long userId);

	ResultData findCommissionRecord(Integer pageNo, Integer pageSize, long userId);

}
