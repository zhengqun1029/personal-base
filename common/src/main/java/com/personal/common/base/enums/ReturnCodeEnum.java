package com.personal.common.base.enums;

import com.personal.common.base.exception.BusinessException;

/**
 * project: com.personal.ssm
 *
 * @author zhenghanbin time: 2018/10/25 14:36
 */
public enum ReturnCodeEnum {
    SUCCESS(12000, "OK", "成功"),
    ERROR_14000(14000, "Bad Request", ""),
    ERROR_14001(14001, "Bad Request Sign", ""),
    ERROR_14002(14002, "Bad Request Parameter", "请求参数异常[%s]"),
    ERROR_14003(14003, "Bad Request SQL", ""),
    ERROR_14004(14004, "Bad Request State", ""),
    ERROR_14010(14010, "Unauthorized", ""),
    ERROR_14011(14011, "Unauthorized Session", ""),
    ERROR_14041(14041, "Not Found Method", ""),
    ERROR_14042(14042, "Not Found Data", ""),
    ERROR_14051(14051, "Method Not Allowed Role", ""),
    ERROR_14052(14052, "Method Not Allowed Data", ""),
    ERROR_15001(15001, "Internal Server Error Unknown", "系统未知错误，请排查"),
    ERROR_15002(15002, "Internal Server Error Impossible", ""),
    ERROR_15021(15021, "Internal Server Remove Monitor", ""),
    ERROR_16001(16001, "交易缺少参数", ""),
    ERROR_16003(16003, "service error", "系统繁忙，请稍后再试或联系相关人员"),
    ERROR_16002(16002, "费率值设置不合法", "费率值设置不合法[%s]"),
    ERROR_16004(16004, "service error", "系统处理异常[%s]"),
    ERROR_16005(16005, "operation is not allow", "操作不允许，[%s]"),
    ERROR_16006(16006, "operation is not allow", "商户银联二维码阶梯费率不可为null"),
    ERROR_16007(16007, "operation is not allow", "商户vip套餐费率计算异常，未找到对应费率信息"),
    ERROR_16008(16008, "http request error", "调用外部接口返回异常！异常信息：[%s]"),
    ERROR_16009(16009, "load database source error!", "加载系统默认配置参数异常!"),
    ERROR_20001(20001, "operation is not allow", "警告：[%s]"),
    ;

    private Integer key;
    /**
     * 响应码描述
     */
    private String desc;
    /**
     * 响应码提示语
     */
    private String remark;

    public Integer getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }

    public String getRemark() {
        return remark;
    }

    ReturnCodeEnum(Integer key, String desc, String remark) {
        this.key = key;
        this.desc = desc;
        this.remark = remark;
    }

    public static ReturnCodeEnum valueof(Integer key) {
        if (key != null) {
            for(ReturnCodeEnum enums : ReturnCodeEnum.values()) {
                if (key.equals(enums.getKey())) {
                    return enums;
                }
            }
        }
        throw new BusinessException(ReturnCodeEnum.ERROR_16004, "系统响应码:" +  key + "不合法!");
    }


}
