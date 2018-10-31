package com.legou.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.legou.common.exception.LeGouException;
import com.legou.common.pojo.DataTablesResult;
import com.legou.manager.mapper.TbOrderItemMapper;
import com.legou.manager.mapper.TbOrderMapper;
import com.legou.manager.mapper.TbOrderShippingMapper;
import com.legou.manager.pojo.TbOrder;
import com.legou.manager.pojo.TbOrderExample;
import com.legou.manager.pojo.TbOrderItem;
import com.legou.manager.pojo.TbOrderItemExample;
import com.legou.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public DataTablesResult getOrderList(int draw, int start, int length, int cid, String search, String orderColumn, String orderDir) {
        List<TbOrder> tbOrder = this.tbOrderMapper.selectByMulti("%"+search+"%",orderColumn,orderDir);
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
        try{
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
        }catch (LeGouException e){
            e.setMsg("删除订单失败!!!");
            return false;
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
            TbOrder order = new TbOrder();
            order.setOrderId(id);
            TbOrderExample tbOrderExample = new TbOrderExample();
            tbOrderExample.createCriteria().andCloseTimeEqualTo(new Date()).andUpdateTimeEqualTo(new Date()).
                    andStatusEqualTo(5);
            this.tbOrderMapper.updateByExampleSelective(order,tbOrderExample);
        }catch (LeGouException e){
            e.setMsg("取消订单失败!!!");
            return false;
        }
        return true;
    }

    @Override
    public Boolean remarkOrder(String orderId, String message) {
        try{
            TbOrder tbOrder = new TbOrder();
            tbOrder.setOrderId(orderId);
            TbOrderExample example = new TbOrderExample();
            example.createCriteria().andBuyerMessageEqualTo(message);
            this.tbOrderMapper.updateByExampleSelective(tbOrder,example);
        }catch (LeGouException e){
            e.setMsg("备注订单失败!!!");
            return false;
        }
        return true;
    }
}
