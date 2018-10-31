package com.legou.manager.service;

import com.legou.common.pojo.DataTablesResult;

/**
 * @Author: clzhang
 * @Date: 2018/10/30 17:47
 */
public interface OrderService {


    DataTablesResult getOrderList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir);

    Boolean orderDel(String id);

    Long orderCount();

    Boolean cancleOrder(String id);

    Boolean remarkOrder(String orderId,String message);
}
