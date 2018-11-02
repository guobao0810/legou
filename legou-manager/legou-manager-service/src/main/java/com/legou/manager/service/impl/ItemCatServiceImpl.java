package com.legou.manager.service.impl;

import com.legou.common.pojo.ZTreeNode;
import com.legou.manager.dto.DtoUtils;
import com.legou.manager.mapper.TbItemCatMapper;
import com.legou.manager.pojo.TbItemCat;
import com.legou.manager.pojo.TbItemCatExample;
import com.legou.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private TbItemCatMapper tbItemCatMapper;

    @Override
    public List<ZTreeNode> getItemCatList(String id) {
        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        example.setOrderByClause("sort_order");
        criteria.andParentIdEqualTo(Long.valueOf(id));
        List<TbItemCat> tbItemCatList = this.tbItemCatMapper.selectByExample(example);
        List<ZTreeNode> zTreeNodeList = new ArrayList<>();
        for(TbItemCat tbItemCat : tbItemCatList){
            ZTreeNode zTreeNode = DtoUtils.itemCatToZTreeNode(tbItemCat);
            zTreeNodeList.add(zTreeNode);
        }
        return zTreeNodeList;
    }
}
