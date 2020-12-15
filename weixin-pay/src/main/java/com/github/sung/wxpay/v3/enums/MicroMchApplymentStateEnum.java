package com.github.sung.wxpay.v3.enums;


/**
 * 小微商户审核状态
 */
public enum MicroMchApplymentStateEnum {

    /**
     * 审核中
     */
    AUDITING("审核中"),
    /**
     * 已驳回
     */
    REJECTED("已驳回"),
    /**
     * 已冻结
     */
    FROZEN("已冻结"),
    /**
     * 待签约
     */
    TO_BE_SIGNED("待签约"),
    /**
     * 完成
     */
    FINISH("完成");

    private String desc;

    MicroMchApplymentStateEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isFinalState(String state) {
        if (FINISH.name().equals(state) ||
                FROZEN.name().equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isSucceedState(String state) {
        if (TO_BE_SIGNED.name().equals(state) || FINISH.name().equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isCanApply(String state) {
        if (REJECTED.name().equals(state)) {
            return true;
        }
        return false;
    }
}
