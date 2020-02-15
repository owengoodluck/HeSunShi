package com.xseagull.boxing.common;

import com.xseagull.boxing.constant.PaymentType;
import com.xseagull.boxing.features.entity.CustomOrderDetail;
import com.xseagull.boxing.features.entity.CustomSpecification;
import com.xseagull.boxing.pay.OrderDetail;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

public class PaymentTypeTest {
    @Test
    public void test(){
        PaymentType type = PaymentType.ALL;
        System.out.println(type.getValue());
        System.out.println(type);
    }

    @Test
    public void test1(){
        CustomSpecification input = new CustomSpecification();
        input.setHeight(1001);
        CustomOrderDetail target = new CustomOrderDetail();
        try {
            BeanUtils.copyProperties(target,input);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        System.out.println(target.getHeight());
    }
}
