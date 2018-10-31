package com.legou.manager.service;

import com.legou.common.pojo.DataTablesResult;
import com.legou.manager.dto.OrderDetail;

import java.math.BigDecimal;

/**
 * @Author: clzhang
 * @Date: 2018/10/30 17:47
 */
public interface OrderService {


    DataTablesResult getOrderList(int draw, int start, int length, String search, String orderCol, String orderDir);

    Boolean orderDel(String id);

    Long orderCount();

    Boolean cancleOrder(String id);

    Boolean remarkOrder(String orderId,String message);

    int oderDerliver(String orderId, BigDecimal postFee, String shippingName, String shippingCode);

    OrderDetail orderDetial(String orderId);
}
