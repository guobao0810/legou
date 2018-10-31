package com.legou.manager.mapper;

import com.legou.manager.pojo.TbThanks;
import com.legou.manager.pojo.TbThanksExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbThanksMapper {
    long countByExample(TbThanksExample example);

    int deleteByExample(TbThanksExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TbThanks record);

    int insertSelective(TbThanks record);

    List<TbThanks> selectByExample(TbThanksExample example);

    TbThanks selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") TbThanks record, @Param("example") TbThanksExample example);

    int updateByExample(@Param("record") TbThanks record, @Param("example") TbThanksExample example);

    int updateByPrimaryKeySelective(TbThanks record);

    int updateByPrimaryKey(TbThanks record);
}