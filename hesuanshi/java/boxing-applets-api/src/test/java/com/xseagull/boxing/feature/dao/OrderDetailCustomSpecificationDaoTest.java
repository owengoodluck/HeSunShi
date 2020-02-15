package com.xseagull.boxing.feature.dao;

import com.xseagull.boxing.features.dao.CustomSpecificationDao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderDetailCustomSpecificationDaoTest {

    @Autowired
    private CustomSpecificationDao csDao;

//    @Test
//    public void testInsert() throws InvocationTargetException, IllegalAccessException {
//        List<CustomSpecification> list = csDao.queryByPage(1, 383);
//
//        CustomSpecification original = list.get(0);
//        OrderDetailCustomSpecification target = OrderDetailCustomSpecification.copyFrom(original);
//        System.out.println(target.getMaterialName());
//        target.setOrderId(1001);
//        this.orderDao.insert(target);
//
//    }

    @Test
    public void testFind(){
    }
}
