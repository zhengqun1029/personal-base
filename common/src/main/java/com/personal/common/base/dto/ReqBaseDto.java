package com.personal.common.base.dto;

/**
 *
 * 请求头
 * Created by lubo on 2016/8/8.
 */
public class ReqBaseDto<T> {
    /**
     * 客户端版本
     */
    private String v;
    /**
     * 客户端名
     */
    private String c;
    /**
     * 客户端ID
     */
    private String d;

    /**
     * 接口签名值
     */
    private String sign;

    protected T data;

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
