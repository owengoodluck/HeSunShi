package com.xseagull.boxing.feature.dao;

import com.xseagull.boxing.features.dao.CustomSpecificationDao;
import com.xseagull.boxing.features.dao.OrderDao;
import com.xseagull.boxing.features.vo.CustomOrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class orderDaoTest {

    @Autowired
    private OrderDao dao;

    @Test
    public void testFind(){
        CustomOrderVO rs = this.dao.findOrderDetailByOutTradeNo("");
    }
}
