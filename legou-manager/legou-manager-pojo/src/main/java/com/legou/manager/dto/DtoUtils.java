package com.legou.manager.dto;

import com.legou.manager.pojo.TbItem;
import com.legou.manager.pojo.TbItemDesc;

import java.util.Date;

public class DtoUtils {

    public static TbItem itemDtoToTbItem(ItemDto itemDto){
        TbItem tbItem = new TbItem();
        tbItem.setTitle(itemDto.getTitle());
        tbItem.setPrice(itemDto.getPrice());
        tbItem.setCid(itemDto.getCid());
        tbItem.setNum(itemDto.getNum());
        if(tbItem.getLimitNum() == null || tbItem.getLimitNum() < 0){
            tbItem.setLimitNum(10);
        }else{
            tbItem.setLimitNum(itemDto.getLimitNum());
        }
        tbItem.setSellPoint(itemDto.getSellPoint());
        tbItem.setImage(itemDto.getImage());
        return tbItem;
    }

    public static TbItemDesc itemDtoToTbItemDesc(ItemDto itemDto){
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(itemDto.getDetail());
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        return tbItemDesc;
    }

}
