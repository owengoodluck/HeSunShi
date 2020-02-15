package com.xseagull.boxing.features.service.impl;

import com.xseagull.boxing.features.dao.OrderPayDao;
import com.xseagull.boxing.features.service.IOrderPayService;
import com.xseagull.boxing.pay.OrderPay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderPayService implements IOrderPayService {

    @Autowired
    private OrderPayDao orderPayDao;

    @Override
    public void insert(OrderPay orderPay) {
        this.orderPayDao.insert(orderPay);
    }

    @Override
    public void updatePaymentStatusByOutTradeNo(String outTradeNo, int paymentStatus) {
        this.orderPayDao.updatePaymentStatusByOutTradeNo(outTradeNo,paymentStatus);
    }

    @Override
    public OrderPay findByOutTradeNo(String outTradeNo) {
        List<OrderPay> list = this.orderPayDao.findByOutTradeNo(outTradeNo);
        if(list.size()>1){
            throw new RuntimeException("Find more than 1 record for outTradeNo="+outTradeNo);
        }else{
            return list.get(0);
        }
    }
}
