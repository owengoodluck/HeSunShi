package com.xseagull.boxing.features.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.ExpressDao;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.dao.OrderDetailDao;
import com.xseagull.boxing.features.dao.OrderExpressDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.entity.OrderExpress;
import com.xseagull.boxing.features.service.IOrderService;
import com.xseagull.boxing.features.vo.OrderDetailVO;
import com.xseagull.boxing.features.vo.OrderVO;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;
import com.xseagull.boxing.util.ServiceMsgUtils;
import com.xseagull.boxing.util.TemplateData;

@Service
@Transactional
public class OrderService implements IOrderService {

	@Value("${templateId}")
	private String templateId;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	@Autowired
	private ExpressDao expressDao;

	@Autowired
	private OrderExpressDao orderExpressDao;

	@Autowired
	private UserDao userDao;

	/**
	 * 订单分页
	 */
	@Override
	public ResultData queryByPage(Integer pageNo, Integer pageSize, String status, String orderNumber) {
		PageHelper.startPage(pageNo, pageSize);
		List<OrderVO> list = orderDao.queryByPage(status, orderNumber);
		PageInfo<OrderVO> page = new PageInfo<OrderVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 根据订单id查询订单详情
	 */
	@Override
	public ResultData findOrderDetailByOrderId(String orderId) {
		return ResultData.success(orderDetailDao.findOrderDetailByOrderId(orderId));
	}

	/**
	 * 查询快递列表
	 */
	@Override
	public ResultData getExpress() {
		return ResultData.success(expressDao.getExpress());
	}

	/**
	 * 发货
	 */
	@Override
	public ResultData orderSend(OrderExpress orderExpress) {
		orderExpressDao.insert(orderExpress);
		orderDao.updateStatus(orderExpress.getOrderId(), 2);
		Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		m.put("keyword1", new TemplateData(orderExpress.getOrderNumber()));
		m.put("keyword2", new TemplateData(orderExpress.getSerial()));
		m.put("keyword3", new TemplateData(expressDao.findNameById(orderExpress.getExpressId())));
		ServiceMsgUtils.sendAppletsMsg(WeiChartUtils.getAppletsAccessToken().getAccess_token(),
				userDao.findOpenidById(orderExpress.getuId()), "nRXKGm8lr4wxpSmyYBMl8XdRtFfVvymLykXvCKq-cxY",
				"pages/orderList/orderList?type=2", orderDetailDao.findFormIdByOrderId(orderExpress.getOrderId()), m);
		return ResultData.success(null);
	}

	/**
	 * 根据订单详情id查询订单详情
	 */
	@Override
	public ResultData findOrderDetailByDetailId(String orderDetailId) {
		return ResultData.success(orderDetailDao.findOrderDetailByDetailId(orderDetailId));
	}

	/**
	 * 改价
	 */
	@Override
	public ResultData updatePrice(OrderDetailVO orderDetailVO) {
		orderDetailDao.updatePrice(orderDetailVO);
		// Map<String, TemplateData> m = new HashMap<String, TemplateData>();
		// m.put("keyword1", new TemplateData(orderDetailVO.getOrderNumber()));
		// m.put("keyword2", new
		// TemplateData(orderDetailVO.getSpecificationName()));
		// m.put("keyword3", new TemplateData(orderDetailVO.getQuantity() +
		// ""));
		// m.put("keyword4", new TemplateData(orderDetailVO.getPrice() + ""));
		// ServiceMsgUtils.sendAppletsMsg(StartupRunner.appletsTokenMap.get("applets_access_token"),
		// userDao.findOpenidById(orderDetailVO.getuId()), templateId,
		// "pages/orderList/orderList?type=0",
		// orderDetailVO.getFormId(), m);
		return ResultData.success(null);
	}

	/**
	 * 订单分页(根据派单情况查询)
	 */
	@Override
	public ResultData queryByPageAndMerchantStatus(Integer pageNo, Integer pageSize, String status,
			String merchantStatus, String orderNumber) {
		PageHelper.startPage(pageNo, pageSize);
		List<OrderVO> list = orderDao.queryByPageAndMerchantStatus(status, merchantStatus, orderNumber);
		PageInfo<OrderVO> page = new PageInfo<OrderVO>(list);
		return ResultData.success(page);
	}

}
