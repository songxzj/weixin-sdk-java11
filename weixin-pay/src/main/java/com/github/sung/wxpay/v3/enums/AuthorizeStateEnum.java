package com.github.sung.wxpay.v3.enums;

/**
 * 商户开户意愿确认状态
 */
public enum AuthorizeStateEnum {


    AUTHORIZE_STATE_UNAUTHORIZED("未授权"),

    AUTHORIZE_STATE_AUTHORIZED("已授权");


    private String desc;

    AuthorizeStateEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static String getDescByName(String name) {
        for (AuthorizeStateEnum authorizeStateEnum : AuthorizeStateEnum.values()) {
            if (authorizeStateEnum.name().equals(name)) {
                return authorizeStateEnum.getDesc();
            }
        }
        return null;
    }
}
