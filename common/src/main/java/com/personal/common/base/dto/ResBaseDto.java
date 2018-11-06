package com.personal.common.base.dto;

import com.personal.common.base.enums.ReturnCodeEnum;

/**
 *
 * 应答
 *
 * Created by lubo on 2016/8/8.
 */
public class ResBaseDto<T> {

    /**
     * 应答Code
     */
    private Integer code;
    /**
     * 应答说明
     */
    private String des;
    /**
     * 特殊处理标识
     */
    private int handlerFlag;
    /**
     * 日志号
     */
    private String logKey;

    protected T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDes() {
        return des;
    }

    public int getHandlerFlag() {
        return handlerFlag;
    }

    public void setHandlerFlag(int handlerFlag) {
        this.handlerFlag = handlerFlag;
    }

    public String getLogKey() {
        return logKey;
    }

    public void setLogKey(String logKey) {
        this.logKey = logKey;
    }

    public void setDes(String des) {
        if (!(code == ReturnCodeEnum.SUCCESS.getKey())) {
            ReturnCodeEnum returnCode = ReturnCodeEnum.valueof(code);
            this.des = String.format(returnCode.getRemark(), des);
        } else if (code == ReturnCodeEnum.SUCCESS.getKey()) {
            this.des = ReturnCodeEnum.SUCCESS.getDesc();
        } else {
            this.des = des;
        }
    }
}
