package com.xseagull.boxing.features.service;


import com.xseagull.boxing.features.entity.SysConfig;
import com.xseagull.boxing.pay.OrderPay;
import org.assertj.core.internal.cglib.asm.$Opcodes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderPayServiceTest {

    @Autowired
    private IOrderPayService orderPayService;

    @Test
    public void testInsert(){
        System.out.println(this.orderPayService.toString());
        OrderPay orderPay = new OrderPay();
        orderPay.setUserId(1111);
        orderPay.setPayType(1);
        orderPay.setPaymentType(1);
        orderPay.setPaymentStatus(0);
        orderPay.setOutTradeNo("1001");
        this.orderPayService.insert(orderPay);
    }

    @Test
    public void testFind(){
        OrderPay orderPay = this.orderPayService.findByOutTradeNo("1001");
    }

    @Test
    public void testUpdate(){
        this.orderPayService.updatePaymentStatusByOutTradeNo("1001",1);
    }
}
