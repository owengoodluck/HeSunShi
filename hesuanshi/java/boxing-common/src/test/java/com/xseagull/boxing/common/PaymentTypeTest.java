package com.xseagull.boxing.common;

import com.xseagull.boxing.constant.PaymentType;
import org.junit.Test;

public class PaymentTypeTest {
    @Test
    public void test(){
        PaymentType type = PaymentType.ALL;
        System.out.println(type.getValue());
        System.out.println(type);
    }
}
