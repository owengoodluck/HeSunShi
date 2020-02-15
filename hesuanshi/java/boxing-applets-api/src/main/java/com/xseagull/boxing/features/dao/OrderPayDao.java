package com.xseagull.boxing.features.dao;

import com.xseagull.boxing.pay.OrderPay;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderPayDao {

    void insert(OrderPay drderPay);

    void updatePaymentStatusByOutTradeNo(@Param("outTradeNo") String OutTradeNo, @Param("paymentStatus") int paymentStatus);

    List<OrderPay> findByOutTradeNo(@Param("outTradeNo")String outTradeNo);

}
