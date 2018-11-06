package com.personal.provider.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.personal.api.ProviderTestService;
import org.springframework.stereotype.Component;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/26 14:07
 */
@Service(interfaceClass = ProviderTestService.class, version = "1.0.0", timeout = 300, retries = 2, loadbalance = "random", owner = "ZhengHanBin")
@Component
public class ProviderTestServiceImpl implements ProviderTestService {
    @Override
    public String getName(String name) {
        return name + "say: \"hello\".";
    }
}
