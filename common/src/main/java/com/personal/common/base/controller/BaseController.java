package com.personal.common.base.controller;

import com.personal.common.base.dto.ResBaseDto;
import com.personal.common.base.enums.ReturnCodeEnum;
import com.personal.common.base.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title: BaseController.class
 * @Package: com.pay.fee.controller
 * @Descriptiom: 控制层基类
 * @author: zhenghanbin
 * @date 2018/1/31 14:45
 */
public abstract class BaseController {

    private static Logger logger = LoggerFactory.getLogger(BaseController.class);

    public ResBaseDto getSuccessResult(Object object) {
        ResBaseDto resultDto = new ResBaseDto();
        resultDto.setCode(ReturnCodeEnum.SUCCESS.getKey());
        resultDto.setDes(ReturnCodeEnum.SUCCESS.getRemark());
        resultDto.setData(object);
        return resultDto;
    }

    public ResBaseDto getSuccessResult(Object object, Integer handlerFlag) {
        ResBaseDto resultDto = new ResBaseDto();
        resultDto.setCode(ReturnCodeEnum.SUCCESS.getKey());
        resultDto.setDes(ReturnCodeEnum.SUCCESS.getRemark());
        resultDto.setData(object);
        resultDto.setHandlerFlag(handlerFlag);
        return resultDto;
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResBaseDto exceptionHandler(Exception e) {
        ResBaseDto resBaseDto = new ResBaseDto();
        if (e instanceof BusinessException) {
            resBaseDto.setCode(((BusinessException) e).getErrorCode());
            resBaseDto.setDes(e.getMessage());
        } else {
            logger.error(ReturnCodeEnum.ERROR_16003.getRemark(), e);
            resBaseDto.setCode(ReturnCodeEnum.ERROR_16003.getKey());
            resBaseDto.setDes(ReturnCodeEnum.ERROR_16003.getRemark());
        }
        return resBaseDto;
    }

}
