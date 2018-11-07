package com.personal.biz.service.impl;

import com.personal.biz.dao.TestNameMapper;
import com.personal.biz.entity.TestName;
import com.personal.biz.service.TestService;
import com.personal.common.base.BaseConstant;
import com.personal.common.base.enums.ReturnCodeEnum;
import com.personal.common.base.exception.BusinessException;
import com.personal.consumer.impl.ConsumerTestServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 14:52
 */
@Service("testService")
public class TestServiceImpl implements TestService {

    private static Logger logger = LoggerFactory.getLogger(TestServiceImpl.class);

//    @Resource
//    private ConsumerTestServiceImpl consumerTestService;

    @Resource
    private TestNameMapper testNameMapper;

    @Override
    public String test(String name) {
        return name;
    }

    @Override
    public String getName(String name) {
//        return consumerTestService.getName(name);
        return null;
    }

    @Override
    public String insertrecord(TestName testName) {
        try {
            testNameMapper.insertSelective(testName);
            return "SUCCESS";
        } catch (BusinessException e) {
            throw e;
        } catch (Exception e) {
            logger.error(BaseConstant.Exception.SERVICE_ERROR, e);
            throw new BusinessException(ReturnCodeEnum.ERROR_16003);
        }
    }
}
