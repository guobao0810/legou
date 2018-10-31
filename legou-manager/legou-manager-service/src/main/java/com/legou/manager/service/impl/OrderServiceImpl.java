package com.legou.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.legou.common.exception.LeGouException;
import com.legou.common.pojo.DataTablesResult;
import com.legou.manager.dto.OrderDetail;
import com.legou.manager.mapper.TbOrderItemMapper;
import com.legou.manager.mapper.TbOrderMapper;
import com.legou.manager.mapper.TbOrderShippingMapper;
import com.legou.manager.pojo.*;
import com.legou.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Author: clzhang
 * @Date: 2018/10/30 17:47
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;

    @Override
    public DataTablesResult getOrderList(int draw, int start, int length, String search, String orderCol, String orderDir) {
        List<TbOrder> tbOrder = this.tbOrderMapper.selectByMulti("%"+search+"%",orderCol,orderDir);
        PageInfo<TbOrder> pageInfo = new PageInfo<>(tbOrder);
        DataTablesResult result = new DataTablesResult();
        result.setRecordsFiltered((int)pageInfo.getTotal());
        //TODO 未获取到失效的订单
        result.setRecordsTotal((int)pageInfo.getTotal());
        result.setDraw(draw);
        result.setData(tbOrder);
        return result;
    }

    @Override
    public Boolean orderDel(String id) {
            if (this.tbOrderMapper.deleteByPrimaryKey(id)!=1){
                throw new LeGouException("删除订单数失败!!!");
            }
            TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
            tbOrderItemExample.createCriteria().andOrderIdEqualTo(id);
            List<TbOrderItem> tbOrderList = tbOrderItemMapper.selectByExample(tbOrderItemExample);
            for (TbOrderItem tbOrderItem : tbOrderList){
                if(tbOrderItemMapper.deleteByPrimaryKey(tbOrderItem.getId())!=1){
                    throw  new LeGouException("删除订单商品失败!!!");
                }
            }
            if(tbOrderShippingMapper.deleteByPrimaryKey(id)!=1){
                throw new LeGouException("删除物流信息失败!!!");
            }
        return true;
    }

    @Override
    public Long orderCount() {
        TbOrderExample example = new TbOrderExample();
        Long count = this.tbOrderMapper.countByExample(example);
        if(count == null){
            throw  new LeGouException("统计订单数量失败!!!");
        }
        return count;
    }

    @Override
    public Boolean cancleOrder(String id) {
        try{
            TbOrder order = this.tbOrderMapper.selectByPrimaryKey(id);
            order.setUpdateTime(new Date());
            order.setCloseTime(new Date());
            order.setStatus(5);
            this.tbOrderMapper.updateByPrimaryKey(order);
        }catch (LeGouException e){
            e.setMsg("取消订单失败!!!");
            return false;
        }
        return true;
    }

    @Override
    public Boolean remarkOrder(String orderId, String message) {
        try{
            TbOrder order = this.tbOrderMapper.selectByPrimaryKey(orderId);
            order.setBuyerMessage(message);
            order.setUpdateTime(new Date());
            this.tbOrderMapper.updateByPrimaryKey(order);
        }catch (LeGouException e){
            e.setMsg("备注订单失败!!!");
            return false;
        }
        return true;
    }

    @Override
    public int oderDerliver(String orderId, BigDecimal postFee, String shippingName, String shippingCode) {
        TbOrder order = this.tbOrderMapper.selectByPrimaryKey(orderId);
        order.setPostFee(postFee);
        order.setShippingCode(shippingCode);
        order.setShippingName(shippingName);
        order.setStatus(3);
        order.setUpdateTime(new Date());
        order.setConsignTime(new Date());
        return this.tbOrderMapper.updateByPrimaryKey(order);
    }

    @Override
    public OrderDetail orderDetial(String orderId) {
        OrderDetail orderDetail = new OrderDetail();
        TbOrder tbOrder = this.tbOrderMapper.selectByPrimaryKey(orderId);
        TbOrderItemExample tbOrderItemExample = new TbOrderItemExample();
        tbOrderItemExample.createCriteria().andOrderIdEqualTo(orderId);
        List<TbOrderItem> tbOrderList = this.tbOrderItemMapper.selectByExample(tbOrderItemExample);
        TbOrderShipping orderShipping = this.tbOrderShippingMapper.selectByPrimaryKey(orderId);
        orderDetail.setTbOrder(tbOrder);
        orderDetail.setTbOrderItemList(tbOrderList);
        orderDetail.setTbOrderShipping(orderShipping);
        return orderDetail;
    }
}
