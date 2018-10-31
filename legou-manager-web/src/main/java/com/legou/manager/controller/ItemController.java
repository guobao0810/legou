package com.legou.manager.controller;

import com.legou.common.pojo.DataTablesResult;
import com.legou.common.utils.ResultUtil;
import com.legou.manager.pojo.TbItem;
import com.legou.manager.service.ItemService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 分页搜索排序获取商品列表
     * @param draw
     * @param start
     * @param length
     * @param cid
     * @param search
     * @param orderCol
     * @param orderDir
     * @param searchItem
     * @param minDate
     * @param maxDate
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public DataTablesResult getItemList(int draw, int start, int length, int cid, @RequestParam("search[value]") String search,
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
        DataTablesResult result=itemService.getItemList(draw,start,length,cid,search,orderColumn,orderDir);
        return result;
    }

    /**
     * 多条件分页搜索排序获取商品列表
     * @param draw
     * @param start
     * @param length
     * @param cid
     * @param searchKey
     * @param minDate
     * @param maxDate
     * @param search
     * @param orderCol
     * @param orderDir
     * @return
     */
    @RequestMapping(value = "/listSearch",method = RequestMethod.GET)
    public DataTablesResult getItemSearchList(int draw, int start, int length,int cid,String searchKey,String minDate,String maxDate,
                                              @RequestParam("search[value]") String search, @RequestParam("order[0][column]") int orderCol,
                                              @RequestParam("order[0][dir]") String orderDir){

        //获取客户端需要排序的列
        String[] cols = {"checkbox","id", "image", "title", "sell_point", "price", "created", "updated", "status"};
        //默认排序列
        String orderColumn = cols[orderCol];
        if(orderColumn == null) {
            orderColumn = "created";
        }
        //获取排序方式 默认为desc(asc)
        if(orderDir == null) {
            orderDir = "desc";
        }
        if(!search.isEmpty()){
            searchKey=search;
        }
        DataTablesResult result=itemService.getItemSearchList(draw,start,length,cid,searchKey,minDate,maxDate,orderColumn,orderDir);
        return result;
    }

    @GetMapping("aaa")
    public List<TbItem> list(){
        List<TbItem> list =  this.itemService.list();
        return list;
    }


    /**
     * 获取所有的商品数量
     * @return
     */
    @RequestMapping(value = "/count",method = RequestMethod.GET)
    public DataTablesResult getItemCount(){
        DataTablesResult result = null;
        try{
            result = this.itemService.getItemCount();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
        return result;
    }

   /* public ResultUtil<TbItem> addItem(Item  tbItem){
       TbItem item =  this.itemService.addItem(tbItem);
        return new ResultUtil<TbItem>().setData(item);
    }*/



}
