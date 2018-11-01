package com.legou.manager.service;

import com.legou.common.pojo.DataTablesResult;
import com.legou.manager.dto.ItemDto;
import com.legou.manager.pojo.TbItem;

import java.util.List;

/**
 * @Author: clzhang
 * @Date: 2018/9/13 17:54
 */
public interface ItemService {

    DataTablesResult getItemList(int draw, int start, int length, int cid,
                                 String search, String orderCol, String orderDir);

    DataTablesResult getItemSearchList(int draw,int start,int length,int cid,
                                       String search,String minDate,String maxDate,
                                       String orderCol,String orderDir);

    List<TbItem> list();

    DataTablesResult getItemCount();

    int itemAdd(ItemDto itemDto);

}
