package com.legou.manager.controller;

import com.legou.common.pojo.ZTreeNode;
import com.legou.manager.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<ZTreeNode> getItemCatList(@RequestParam(name = "id",defaultValue = "0") String id){
        List<ZTreeNode> zTreeNodeList = this.itemCatService.getItemCatList(id);
        return zTreeNodeList;
    }



}
