package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.OrderExpress;
import com.xseagull.boxing.features.vo.OrderDetailVO;

public interface IOrderService {

	ResultData queryByPage(Integer pageNo, Integer pageSize, String status, String orderNumber);

	ResultData findOrderDetailByOrderId(String orderId);

	ResultData getExpress();

	ResultData orderSend(OrderExpress orderExpress);

	ResultData findOrderDetailByDetailId(String orderDetailId);

	ResultData updatePrice(OrderDetailVO orderDetailVO);

	ResultData queryByPageAndMerchantStatus(Integer pageNo, Integer pageSize, String status, String merchantStatus,
			String orderNumber);

}
