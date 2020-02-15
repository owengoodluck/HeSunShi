package com.xseagull.boxing.features.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xseagull.boxing.constant.OrderStatusEnum;
import com.xseagull.boxing.constant.PaymentType;
import com.xseagull.boxing.features.entity.*;
import com.xseagull.boxing.features.service.IOrderPayService;
import com.xseagull.boxing.pay.OrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.dao.OrderDetailDao;
import com.xseagull.boxing.features.dao.UserDao;
import com.xseagull.boxing.features.service.IOrderService;
import com.xseagull.boxing.features.vo.CustomOrderVO;
import com.xseagull.boxing.features.vo.OrderDetailVO;
import com.xseagull.boxing.pay.OrderPay;
import com.xseagull.boxing.pay.weixin.Prepay;
import com.xseagull.boxing.pay.weixin.WeiChartUtils;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {

	@Value("${orderNotify}")
	private String orderNotify;

	@Autowired
	private UserDao userDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private OrderDetailDao orderDetailDao;

	@Autowired
	private IOrderPayService orderPayService;

	/**
	 * 确认样品
	 */
	public void confirmSample( long orderId ,Boolean confirmResult, long userId){
		CustomOrderVO order = this.orderDao.findByOrderId(orderId);
		if(order == null){
			throw new RuntimeException("orderId "+orderId+"不存在");
		}

		if( order.getStatus().equals( OrderStatusEnum.PENDING_SAMPLE_CONFIRM.getValue())) {
			//只有带收货（样品）状态，才能调用此方法
			if(order.getUserId() != userId){
				throw new RuntimeException("非订单用户，禁止操作");
			}

			if(confirmResult){
				// 样品确认 满意
				this.orderDao.updateOrderStatus(order.getId(), OrderStatusEnum.PENDING_PRODUCE.getValue());
			}else{
				// 样品确认 不满意，状态调整为 等待发货
//				this.orderDao.updateOrderStatus(order.getId(),OrderStatusEnum.PENDING_DELIVER_SAMPLE.getValue());
			}
		}else{
			throw new RuntimeException("订单状态异常，暂时无法进行样品确认，当前订单状态是： " + order.getStatus());
		}
	}

	/**
	 * 支付尾款
	 */
	public void payRest( long orderId ,Double amount){
		CustomOrderVO order = this.orderDao.findByOrderId(orderId);
		if(order == null){
			throw new RuntimeException("orderId "+orderId+"不存在");
		}


	}



	/**
	 * 提交订单--->生成订单记录
	 */
	@Transactional
	@Override
	public ResultData submitOrder(CustomOrder customOrder) {
	    //1. create order
		customOrder.setOrderNumber(String.valueOf(System.currentTimeMillis()));
		customOrder.setBusinessNumber(String.valueOf(System.currentTimeMillis()));
		orderDao.insertOrder(customOrder);

		//2. create OrderDetailCustomSpecification (order datail)
		createOrderDetail(customOrder);

        //3.create OrderPay record
		User user = userDao.findUserById(String.valueOf(customOrder.getUserId()));
        OrderPay orderPay = this.createOrderPay(customOrder,user.getOpenid());

		//4. 微信预订单
		Map<String, String> map = WeiChartUtils.orderPay(orderPay);
		if (map.get("result_code").equals("SUCCESS")) {
			String appid = map.get("appid");
			String nonce_str = map.get("nonce_str");
			String prepay_id = map.get("prepay_id");
			String timestamp = WeiChartUtils.getTenTimes();
			String extended = "prepay_id=" + prepay_id;
			String signType = "MD5";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("appId", appid);
			reqMap.put("nonceStr", nonce_str);
			reqMap.put("package", extended);
			reqMap.put("signType", signType);
			reqMap.put("timeStamp", timestamp);
			String sign = WeiChartUtils.getSign(reqMap);
			// 预下单成功，返回给前端的参数
			Prepay prepay = new Prepay();
			prepay.setAppid(appid);
			prepay.setExtended(extended);
			prepay.setNonce_str(nonce_str);
			prepay.setPrepay_id(prepay_id);
			prepay.setSign(sign);
			prepay.setTimestamp(timestamp);
			prepay.setOutTradeNo(orderPay.getOutTradeNo());
			return ResultData.success(prepay);
		} else {
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		}
	}

	private void createOrderDetail(CustomOrder customOrder){
		if(customOrder==null){
			return;
		}
		List<CustomSpecification> commodityList = (List<CustomSpecification>) JSONArray
				.parseArray(customOrder.getCustomList(), CustomSpecification.class);
		for (CustomSpecification customSpecification : commodityList) {
			CustomOrderDetail target = new CustomOrderDetail();
			target.setOrderId(customOrder.getId());
			try {
				BeanUtils.copyProperties(target,customSpecification);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			}
			this.orderDetailDao.insert(target);
		}
	}

	/**
	 *
	 * @param customOrder
	 * @param openid
	 * @return
	 */
	private OrderPay createOrderPay(CustomOrder customOrder,String openid){
        OrderPay orderPay = new OrderPay();
        orderPay.setOpenid(openid);
        orderPay.setUserId(customOrder.getUserId());

        orderPay.setNotifyUrl(orderNotify);
        orderPay.setPaymentStatus(0);//'0:"等待确认"  1:"成功"  2:"失败"'
        orderPay.setOutTradeNo(  customOrder.getPaymentType()+"_"+String.valueOf(System.currentTimeMillis()) );
        orderPay.setInternalTradeNo(String.valueOf(customOrder.getId()));

        List<CustomSpecification> commodityList = (List<CustomSpecification>) JSONArray
                .parseArray(customOrder.getCustomList(), CustomSpecification.class);
        double totalPrice = 0.0;
        double totalAdvancePayment=0.0;
        for (CustomSpecification customSpecification : commodityList) {
            if (customOrder.getFareType() == 0) {
                totalPrice = totalPrice + customSpecification.getPrice() + customSpecification.getFare();
            } else {
                totalPrice = totalPrice + customSpecification.getPrice();
            }
            totalAdvancePayment += customSpecification.getAdvancePaymentPrice();
        }
        if(PaymentType.ALL.getValue().equals(customOrder.getPaymentType())){
            orderPay.setBody("全款支付，订单编号:" + customOrder.getOrderNumber());
            orderPay.setTotalAmount(totalPrice);
            orderPay.setPaymentType(1);//1:"全款"  2:"定金"  3:"尾款"
        }else if( PaymentType.ADVANCE.getValue().equals(customOrder.getPaymentType()) ){
            orderPay.setBody("定金支付，订单编号:" + customOrder.getOrderNumber());
            orderPay.setPaymentType(2);//1:"全款"  2:"定金"  3:"尾款"
            orderPay.setTotalAmount(totalAdvancePayment);
        }else if( PaymentType.REST.getValue().equals(customOrder.getPaymentType()) ){
            orderPay.setBody("尾款支付，订单编号:" + customOrder.getOrderNumber());
            orderPay.setPaymentType(3);//1:"全款"  2:"定金"  3:"尾款"
            orderPay.setTotalAmount(totalPrice - totalAdvancePayment);
        }else {
            throw new RuntimeException("invalid payment type code :"+customOrder.getOrderNumber());
        }

        this.orderPayService.insert(orderPay);
        return orderPay;
    }

	/**
	 * 用户订单列表
	 */
	@Override
	public ResultData findOrderByStatus(Integer pageNo, Integer pageSize, int status, long userId) {
		PageHelper.startPage(pageNo, pageSize);
		List<CustomOrderVO> list = orderDao.findOrderByStatus(status, userId);
		for (CustomOrderVO customOrderVO : list) {
			List<OrderDetailVO> orderDetailVO = customOrderVO.getOrderDetailVO();
			for (OrderDetailVO orderDetailVO2 : orderDetailVO) {
				if (customOrderVO.getFareType() == 0) {
					customOrderVO.setCountPrice(
							customOrderVO.getCountPrice().add(orderDetailVO2.getPrice()).add(orderDetailVO2.getFare()));
				} else {
					customOrderVO.setCountPrice(customOrderVO.getCountPrice().add(orderDetailVO2.getPrice()));
				}
			}
		}
		PageInfo<CustomOrderVO> page = new PageInfo<CustomOrderVO>(list);
		return ResultData.success(page);
	}

	/**
	 * 根据微信交易编号查询订单详情
	 */
	@Override
	public ResultData findOrderDetailByOutTradeNo(String outTradeNo) {
		OrderPay orderPay = this.orderPayService.findByOutTradeNo(outTradeNo);
		if(orderPay == null){
			throw new RuntimeException("Not able to find OrderPay by outTradeNo="+outTradeNo);
		}

		CustomOrderVO customOrderVO = orderDao.findOrderDetailByOutTradeNo(orderPay.getInternalTradeNo());
		List<OrderDetailVO> orderDetailVO = customOrderVO.getOrderDetailVO();
		for (OrderDetailVO orderDetailVO2 : orderDetailVO) {
			if (customOrderVO.getFareType() == 0) {
				customOrderVO.setCountPrice(
						customOrderVO.getCountPrice().add(orderDetailVO2.getPrice()).add(orderDetailVO2.getFare()));
			} else {
				customOrderVO.setCountPrice(customOrderVO.getCountPrice().add(orderDetailVO2.getPrice()));
			}
		}
		return ResultData.success(customOrderVO);
	}

	/**
	 * 取消订单
	 */
	@Override
	public ResultData quxiaoOrder(long orderId) {
		orderDao.updateOrderStatus(orderId, 4);
		return ResultData.success(null);
	}

	/**
	 * 确认收货
	 */
	@Override
	public ResultData confirmReceipt(long orderId) {
		// 修改订单表状态的同时修改订单详情状态和佣金记录状态
		return ResultData.success(orderDao.updateOrderAndDetail(orderId));
	}

	/**
	 * 付款--->列表上点付款
	 */
	@Override
	public ResultData secondPay(SecondPay secondPay) {
		System.out.println(secondPay);
		User user = userDao.findUserById(secondPay.getUserId() + "");
		String string = String.valueOf(System.currentTimeMillis());
		orderDao.updateBusinessNumberById(secondPay.getOrderId(), string);
		OrderPay orderPay = new OrderPay();
		orderPay.setNotifyUrl(orderNotify);
		orderPay.setOpenid(user.getOpenid());
		orderPay.setOutTradeNo(string);
		orderPay.setBody("订单编号:" + secondPay.getOrderNumber());
		orderPay.setTotalAmount(secondPay.getCountPrice());
		// orderPay.setTotalAmount(0.01);
		// 微信预订单
		Map<String, String> map = WeiChartUtils.orderPay(orderPay);
		if (map.get("result_code").equals("SUCCESS")) {
			String appid = map.get("appid");
			String nonce_str = map.get("nonce_str");
			String prepay_id = map.get("prepay_id");
			String timestamp = WeiChartUtils.getTenTimes();
			String extended = "prepay_id=" + prepay_id;
			String signType = "MD5";
			Map<String, String> reqMap = new HashMap<String, String>();
			reqMap.put("appId", appid);
			reqMap.put("nonceStr", nonce_str);
			reqMap.put("package", extended);
			reqMap.put("signType", signType);
			reqMap.put("timeStamp", timestamp);
			String sign = WeiChartUtils.getSign(reqMap);
			// 预下单成功，返回给前端的参数
			Prepay prepay = new Prepay();
			prepay.setAppid(appid);
			prepay.setExtended(extended);
			prepay.setNonce_str(nonce_str);
			prepay.setPrepay_id(prepay_id);
			prepay.setSign(sign);
			prepay.setTimestamp(timestamp);
			prepay.setOutTradeNo(orderPay.getOutTradeNo());
			return ResultData.success(prepay);
		} else {
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * 查询不同状态订单的条数
	 */
	@Override
	public ResultData findOrderNum(long userId) {
		return ResultData.success(orderDao.findOrderNum(userId));
	}

	/**
	 * 根据订单id查询收货信息
	 */
	@Override
	public ResultData selectAddressById(long id) {
		return ResultData.success(orderDao.selectAddressById(id));
	}

}
