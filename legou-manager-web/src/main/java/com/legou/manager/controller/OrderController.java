package com.legou.manager.controller;

import com.legou.common.pojo.DataTablesResult;
import com.legou.common.pojo.Result;
import com.legou.common.utils.ResultUtil;
import com.legou.manager.dto.OrderDetail;
import com.legou.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * @Author: clzhang
 * @Date: 2018/10/30 17:42
 */
@RequestMapping("/order")
@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public DataTablesResult getOrderList(int draw, int start, int length,@RequestParam("search[value]") String search,
                                         @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","order_id", "payment","shipping_code", "user_id", "buyer_nick", "create_time", "update_time", "payment_time", "close_time","end_time","status"};
        String orderColumn = cols[orderCol];
        //默认排序列
        if(orderColumn == null) {
            orderColumn = "create_time";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResult result= orderService.getOrderList(draw,start,length,search,orderColumn,orderDir);
        return result;
    }
    /**
     * 批量删除订单
     * @param ids
     * @return
     */
    @RequestMapping(value = "/del/{ids}" , method = RequestMethod.DELETE)
    public Result<Object> orderDel(@PathVariable String[] ids){
        Boolean flag = false;
        for(String id : ids){
            flag = this.orderService.orderDel(id);
        }
        return new ResultUtil<Object>().setData(null);
    }

    /**
     * 统计订单数量
     * @return
     */
    @RequestMapping(value = "/count" ,method = RequestMethod.GET)
    public Result<Long> orderCount(){
        Long result = this.orderService.orderCount();
        return new ResultUtil<Long>().setData(result);
    }

    /**
     * 取消订单
     * @param id
     * @return
     */
    @RequestMapping(value = "/cancel/{orderId}" , method = RequestMethod.GET)
    public Result<Object> cancleOrder(@PathVariable("orderId") String id){
        Boolean flag = false;
        flag = this.orderService.cancleOrder(id);
        if(flag){
            return new ResultUtil<Object>().setData(null);
        }else{
            return new ResultUtil<Object>().setErrorMsg("删除失败!!!");
        }
    }

    /**
     * 订单备注
     * @param orderId
     * @param message
     * @return
     */
    @RequestMapping(value = "/remark" , method = RequestMethod.POST)
    public Result<Object> remarkOrder(@RequestParam("orderId") String orderId,
                                      @RequestParam(value = "message",required = false) String message){
        Boolean flag = false;
        flag = this.orderService.remarkOrder(orderId , message);
        if(flag){
            return new ResultUtil<Object>().setData(null);
        }else{
            return new ResultUtil<Object>().setErrorMsg("备注失败!!!");
        }
    }

    /**
     * 订单开始发货
     * @param orderId
     * @param postFee
     * @param shippingName
     * @param shippingCode
     * @return
     */
    @RequestMapping(value = "/deliver",method = RequestMethod.POST)
    public Result<Object> orderDeliver(@RequestParam("orderId") String orderId,
                                       @RequestParam("postFee") BigDecimal postFee,
                                       @RequestParam("shippingName") String shippingName,
                                       @RequestParam("shippingCode") String shippingCode){
        if(this.orderService.oderDerliver(orderId,postFee,shippingName,shippingCode) == 1){
            return new ResultUtil<Object>().setData(null);
        }else{
            return new ResultUtil<Object>().setErrorMsg("不好意思，发货失败!!!");
        }
    }

    /**
     * 获取订单的模版详情
     * @param orderId
     * @return
     */
    @RequestMapping(value = "/detail/{orderId}",method = RequestMethod.GET)
    public Result<Object> orderDetail(@PathVariable String orderId){
        OrderDetail orderDetail = this.orderService.orderDetial(orderId);
        if(orderDetail != null){
            return new ResultUtil<Object>().setData(orderDetail);
        }else{
            return new ResultUtil<Object>().setErrorMsg("不好意思，获取订单详情失败!!!");
        }
    }

}
