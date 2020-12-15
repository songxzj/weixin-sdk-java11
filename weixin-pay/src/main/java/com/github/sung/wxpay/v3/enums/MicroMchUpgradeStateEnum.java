package com.github.sung.wxpay.v3.enums;

/**
 * 小微升级审核状态
 */
public enum MicroMchUpgradeStateEnum {

    /**
     * 资料校验中
     */
    CHECKING("资料校验中"),

    /**
     * 待账户验证
     */
    ACCOUNT_NEED_VERIFY("待账户验证"),

    /**
     * 审核中
     */
    AUDITING("审核中"),

    /**
     * 已驳回
     */
    REJECTED("已驳回"),

    /**
     * 待签约
     */
    NEED_SIGN("待签约"),

    /**
     * 完成
     */
    FINISH("完成"),

    /**
     * 已冻结
     */
    FROZEN("已冻结");

    private String desc;

    MicroMchUpgradeStateEnum(String desc) {
        this.desc = desc;
    }

    public static boolean isFinalState(String state) {
        if (FINISH.name().equals(state) ||
                FROZEN.name().equals(state)) {
            return true;
        }
        return false;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isCanApply(String state) {
        if (REJECTED.name().equals(state)) {
            return true;
        }
        return false;
    }
}
