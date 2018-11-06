package com.personal.consumer.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.personal.api.ProviderTestService;
import org.springframework.stereotype.Component;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/29 9:40
 */
@Component
public class ConsumerTestServiceImpl {


    @Reference(version = "1.0.0")
    private ProviderTestService providerTestService;

    public String getName(String name) {
        return "consumer get response message : \"" + providerTestService.getName(name) + "\"";
    }

}
