package com.github.sung.wxpay.v3.enums;

/**
 * 小微经营类型
 */
public enum MicroManagementTypeEnum {

    /**
     * 门店场所
     */
    MICRO_TYPE_STORE(1L),

    /**
     * 流动经营/便民服务
     */
    MICRO_TYPE_MOBILE(2L),

    /**
     * 线上商品/服务交易
     */
    MICRO_TYPE_ONLINE(3L);

    private Long code;

    public Long getCode() {
        return code;
    }

    MicroManagementTypeEnum(Long code) {
        this.code = code;
    }


    public static String getByCode(Long code) {
        for (MicroManagementTypeEnum microManagementTypeEnum : MicroManagementTypeEnum.values()) {
            if (microManagementTypeEnum.code.equals(code)) {
                return microManagementTypeEnum.name();
            }
        }
        return null;
    }
}
