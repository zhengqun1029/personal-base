package com.personal.biz.service;

import com.personal.biz.entity.TestName;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/31 17:32
 */
public interface TestService {

    String test(String name);

    String getName(String name);

    String insertrecord(TestName testName);


}
