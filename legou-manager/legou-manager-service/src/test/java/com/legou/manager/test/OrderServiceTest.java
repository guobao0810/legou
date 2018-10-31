package com.legou.manager.test;

import com.legou.manager.service.OrderService;
import com.legou.manager.service.impl.OrderServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class OrderServiceTest {

    /*public OrderServiceImpl getOrderService(){
        return new OrderServiceImpl();
    }*/

    @Autowired
    private OrderService orderService;

    @Test
    public void orderCount(){
        Long count = this.orderService.orderCount();
        System.out.print(count);
    }

}
