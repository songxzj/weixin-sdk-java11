package com.github.songxchn.wxpay.v3.enums;


/**
 * 结算银行账户类型
 */
public enum  BankAccountTypeEnum {


    /**
     * 对公银行账户
     */
    BANK_ACCOUNT_TYPE_CORPORATE(1L),


    /**
     * 经营者个人银行卡
     */
    BANK_ACCOUNT_TYPE_PERSONAL(2L);

    private Long code;

    BankAccountTypeEnum(Long code) {
        this.code = code;
    }

    public static String getByCode(Long code) {
        for (BankAccountTypeEnum bankAccountTypeEnum : BankAccountTypeEnum.values()) {
            if (bankAccountTypeEnum.code.equals(code)) {
                return bankAccountTypeEnum.name();
            }
        }
        return null;
    }


}
