package com.github.sung.wxpay.v3.enums;

/**
 * 开户银行
 */
public enum BankEnum {

    工商银行,

    交通银行,

    招商银行,

    民生银行,

    中信银行,

    浦发银行,

    兴业银行,

    光大银行,

    广发银行,

    平安银行,

    北京银行,

    华夏银行,

    农业银行,

    建设银行,

    邮政储蓄银行,

    中国银行,

    宁波银行,

    其他银行;

    public static String getByName(String name) {
        for (BankEnum bankEnum : BankEnum.values()) {
            if (bankEnum.name().equals(name)) {
                return bankEnum.name();
            }
        }
        return 其他银行.name();
    }


}
