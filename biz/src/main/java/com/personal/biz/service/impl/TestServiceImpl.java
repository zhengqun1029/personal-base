package com.personal.biz.service.impl;

import com.personal.biz.service.TestService;
import com.personal.consumer.impl.ConsumerTestServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 14:52
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    @Resource
    private ConsumerTestServiceImpl consumerTestService;

    @Override
    public String test(String name) {
        return name;
    }

    @Override
    public String getName(String name) {
        return consumerTestService.getName(name);
    }
}
