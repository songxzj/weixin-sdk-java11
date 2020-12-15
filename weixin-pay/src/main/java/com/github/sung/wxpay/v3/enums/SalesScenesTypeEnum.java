package com.github.sung.wxpay.v3.enums;


/**
 * 经营场景类型
 */
public enum SalesScenesTypeEnum {

    /**
     * 线下门店
     */
    SALES_SCENES_STORE(1L),

    /**
     * 公众号
     */
    SALES_SCENES_MP(2L),

    /**
     * 小程序
     */
    SALES_SCENES_MINI_PROGRAM(3L),

    /**
     * 互联网
     */
    SALES_SCENES_WEB(4L),

    /**
     * APP
     */
    SALES_SCENES_APP(5L),

    /**
     * 企业微信
     */
    SALES_SCENES_WEWORK(6L);

    private Long code;

    SalesScenesTypeEnum(Long code) {
        this.code = code;
    }

    public static String getByCode(Long code) {
        for (SalesScenesTypeEnum salesScenesTypeEnum : SalesScenesTypeEnum.values()) {
            if (salesScenesTypeEnum.code.equals(code)) {
                return salesScenesTypeEnum.name();
            }
        }
        return null;
    }
}
