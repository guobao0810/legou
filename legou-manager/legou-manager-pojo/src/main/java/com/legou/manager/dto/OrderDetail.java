package com.legou.manager.dto;

import com.legou.manager.pojo.TbOrder;
import com.legou.manager.pojo.TbOrderItem;
import com.legou.manager.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

public class OrderDetail implements Serializable {

    private TbOrder tbOrder;

    private List<TbOrderItem> tbOrderItemList;

    private TbOrderShipping tbOrderShipping;

    public TbOrder getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrder tbOrder) {
        this.tbOrder = tbOrder;
    }

    public List<TbOrderItem> getTbOrderItemList() {
        return tbOrderItemList;
    }

    public void setTbOrderItemList(List<TbOrderItem> tbOrderItemList) {
        this.tbOrderItemList = tbOrderItemList;
    }

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }
}
