package com.personal.common.base;

/**
 * @Title: BaseConstant.class
 * @Descriptiom: 公共常量
 * @author: zhenghanbin
 * @date 2018/3/6 9:11
 */
public interface BaseConstant {
    // 框架常量
    interface Framework {
        // 日志常量
        interface Logger {
            // 日志唯一标识ID
            String LOGGER_TOKEN_KEY = "loggerTokenId";
        }
    }
    // 异常常量
    interface Exception {
        String SERVICE_ERROR = "服务器内部异常!";
    }



}
