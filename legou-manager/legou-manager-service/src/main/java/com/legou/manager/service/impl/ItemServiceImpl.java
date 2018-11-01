package com.legou.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.legou.common.exception.LeGouException;
import com.legou.common.pojo.DataTablesResult;
import com.legou.common.utils.IDUtils;
import com.legou.manager.dto.DtoUtils;
import com.legou.manager.dto.ItemDto;
import com.legou.manager.mapper.TbItemDescMapper;
import com.legou.manager.mapper.TbItemMapper;
import com.legou.manager.pojo.TbItem;
import com.legou.manager.pojo.TbItemDesc;
import com.legou.manager.pojo.TbItemExample;
import com.legou.manager.service.ItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author: clzhang
 * @Date: 2018/9/13 19:14
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private TbItemMapper itemMapper;

    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public DataTablesResult getItemList(int draw, int start, int length, int cid, String search,
                                        String orderCol, String orderDir) {

        DataTablesResult result=new DataTablesResult();

        //分页执行查询返回结果
        PageHelper.startPage(start/length+1,length);
        List<TbItem> list = itemMapper.selectItemByCondition(cid,"%"+search+"%",orderCol,orderDir);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getItemCount().getRecordsTotal());

        result.setDraw(draw);
        result.setData(list);

        return result;
    }

    @Override
    public DataTablesResult getItemSearchList(int draw, int start, int length,int cid, String search,
                                              String minDate, String maxDate, String orderCol, String orderDir) {

        DataTablesResult result=new DataTablesResult();

        //分页执行查询返回结果
        PageHelper.startPage(start/length+1,length);
        List<TbItem> list = itemMapper.selectItemByMultiCondition(cid,"%"+search+"%",minDate,maxDate,orderCol,orderDir);
        PageInfo<TbItem> pageInfo=new PageInfo<>(list);
        result.setRecordsFiltered((int)pageInfo.getTotal());
        result.setRecordsTotal(getItemCount().getRecordsTotal());

        result.setDraw(draw);
        result.setData(list);

        return result;
    }
    @Override
    public List<TbItem> list() {
        return this.itemMapper.selectByExample(null);
    }

    public DataTablesResult getItemCount(){
        DataTablesResult result = new DataTablesResult();
        TbItemExample tbItemExample = new TbItemExample();
        long count = itemMapper.countByExample(tbItemExample);
        result.setRecordsTotal(Math.toIntExact(count));
        return result;
    }

    @Override
    public int itemAdd(ItemDto itemDto) {
        Long itemId = IDUtils.getId();
        TbItem tbItem = DtoUtils.itemDtoToTbItem(itemDto);
        tbItem.setId(itemId);
        tbItem.setUpdated(new Date());
        tbItem.setCreated(new Date());
        tbItem.setStatus((byte)1);
        if(StringUtils.isEmpty(tbItem.getImage())){
            tbItem.setImage("http://ow2h3ee9w.bkt.clouddn.com/nopic.jpg");
        }
        if(this.itemMapper.insert(tbItem) != 1){
            throw new LeGouException("添加商品失败!!!");
        }
        TbItemDesc tbItemDesc = DtoUtils.itemDtoToTbItemDesc(itemDto);
        tbItemDesc.setItemId(itemId);
        if (this.tbItemDescMapper.insert(tbItemDesc) != 1) {
            throw new LeGouException("添加商品描述失败!!!");
        }
        return 1;
    }


}
