package com.personal.biz.dao;

import com.personal.biz.entity.TestName;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TestNameMapper {

    int insert(TestName record);

    int insertSelective(TestName record);
}