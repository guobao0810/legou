package com.legou.manager.controller;

import com.legou.common.pojo.DataTablesResult;
import com.legou.common.pojo.Result;
import com.legou.common.utils.ResultUtil;
import com.legou.manager.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public DataTablesResult getOrderList(int draw, int start, int length, int cid, @RequestParam("search[value]") String search,
                                         @RequestParam("order[0][column]") int orderCol, @RequestParam("order[0][dir]") String orderDir,
                                         String searchItem, String minDate, String maxDate){
        //获取客户端需要排序的列
        String[] cols = {"checkbox","id", "image", "title", "sell_point", "price", "created", "updated", "status"};
        String orderColumn = cols[orderCol];
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        DataTablesResult result = this.orderService.getOrderList(draw,start,length,cid,search,orderColumn,orderDir);
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
        if(flag){
            return new ResultUtil<>().setData(null);
        }else{
            return new ResultUtil<>().setErrorMsg("不好意思，删除失败!!!");
        }
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

    @RequestMapping(value = "/cancle/{id}" , method = RequestMethod.GET)
    public Result<Object> cancleOrder(@PathVariable String id){
        Boolean flag = false;
        flag = this.orderService.cancleOrder(id);
        if(false){
            return new ResultUtil<Object>().setData(null);
        }else{
            return new ResultUtil<Object>().setErrorMsg("删除失败!!!");
        }
    }

    @RequestMapping(value = "/remark" , method = RequestMethod.POST)
    public Result<Object> remarkOrder(@RequestParam("orderId") String orderId,
                                      @RequestParam(value = "message",required = false) String message){
        Boolean flag = false;
        flag = this.orderService.remarkOrder(orderId , message);
        if(false){
            return new ResultUtil<Object>().setData(null);
        }else{
            return new ResultUtil<Object>().setErrorMsg("备注失败!!!");
        }
    }

}
