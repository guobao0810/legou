package com.legou.manager.dto;

import com.legou.common.pojo.ZTreeNode;
import com.legou.manager.pojo.TbItem;
import com.legou.manager.pojo.TbItemCat;
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

    public static ZTreeNode itemCatToZTreeNode(TbItemCat tbItemCat){
        ZTreeNode zTreeNode = new ZTreeNode();
        zTreeNode.setId(tbItemCat.getId());
        zTreeNode.setpId(tbItemCat.getParentId());
        zTreeNode.setStatus(tbItemCat.getStatus());
        zTreeNode.setRemark(tbItemCat.getRemark());
        zTreeNode.setName(tbItemCat.getName());
        zTreeNode.setParent(tbItemCat.getIsParent());
        zTreeNode.setSortOrder(tbItemCat.getSortOrder());
        return zTreeNode;
    }

}
