package com.personal.common.base.exception;

import com.personal.common.base.enums.ReturnCodeEnum;

/**
 * @Title: BusinessException.class
 * @Package: com.pay.fee.org.ssm.zhb.common.basic.exception
 * @Descriptiom: 业务处理异常
 * @author: zhenghanbin
 * @date 2018/1/31 11:07
 */
public class BusinessException extends BaseException {

    public BusinessException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);

    }

    public BusinessException(ReturnCodeEnum returnCode, String errorMsg) {
        super(returnCode.getKey(), errorMsg);

    }

    public BusinessException(int errorCode) {
        super(errorCode);

    }

    public BusinessException(ReturnCodeEnum returnCode) {
        super(returnCode.getKey(), returnCode.getRemark());

    }


    public BusinessException(String errorMsg) {
        super(errorMsg);
    }

    public BusinessException(Throwable cause, int errorCode, String errorMsg,
                             String traceId) {
        super(cause, errorCode, errorMsg, traceId);
    }

    public BusinessException(Throwable cause, int errorCode, String errorMsg) {
        super(cause, errorCode, errorMsg);
    }

    public BusinessException(Throwable cause, String errorMsg) {
        super(cause, errorMsg);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

}
