package com.xseagull.boxing.features.service;

import com.xseagull.boxing.pay.OrderPay;

import java.util.List;

public interface IOrderPayService {

    void insert(OrderPay orderPay);

    void updatePaymentStatusByOutTradeNo(String outTradeNo, int paymentStatus);

    OrderPay findByOutTradeNo(String outTradeNo);
}
