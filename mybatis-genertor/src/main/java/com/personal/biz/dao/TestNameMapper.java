package com.personal.biz.dao;

import com.personal.biz.entity.TestName;

public interface TestNameMapper {
    int insert(TestName record);

    int insertSelective(TestName record);
}