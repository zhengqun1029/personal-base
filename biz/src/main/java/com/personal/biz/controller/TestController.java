package com.personal.biz.controller;

import com.personal.biz.entity.TestName;
import com.personal.biz.service.TestService;
import com.personal.common.base.controller.BaseController;
import com.personal.common.base.dto.ResBaseDto;
import com.personal.common.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 14:36
 */
@RestController
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;


    @RequestMapping(value = "/heartbeat.do", method = RequestMethod.GET)
    public String heartbeat() {
        return "SUCCESS";
    }

    @RequestMapping(value = "/getName.do", method = RequestMethod.GET)
    public ResBaseDto getName(String name) {
        return getSuccessResult(testService.getName(name));
    }

    @RequestMapping(value = "/insertName.do", method = RequestMethod.POST)
    public ResBaseDto insertName(@RequestBody TestName name) {
        name.setId(SnowflakeIdWorker.getInstance(0,0).nextId());
        return getSuccessResult(testService.insertrecord(name));
    }

}
