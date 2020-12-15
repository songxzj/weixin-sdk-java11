package com.github.sung.wxpay.v3.enums;

/**
 * 商户开户意愿审核状态
 */
public enum MchApplymentAuthorizeStateEnum {

    /**
     * 审核中：请耐心等待1~2个工作日，微信支付将会完成审核。
     */
    APPLYMENT_STATE_WAITTING_FOR_AUDIT("审核中"),

    /**
     * 编辑中：可能提交申请发生了错误导致，可用同一个业务申请编号重新提交。
     */
    APPLYMENT_STATE_EDITTING("编辑中"),

    /**
     * 待确认联系信息：请扫描微信支付返回的小程序码确认联系信息(此过程可修改超级管理员手机号)。
     */
    APPLYMENT_STATE_WAITTING_FOR_CONFIRM_CONTACT("待确认联系信息"),

    /**
     * 待账户验证：请扫描微信支付返回的小程序码在小程序端完成账户验证。
     */
    APPLYMENT_STATE_WAITTING_FOR_CONFIRM_LEGALPERSON("待账户验证"),

    /**
     * 审核通过：请扫描微信支付返回的小程序码在小程序端完成授权流程。
     */
    APPLYMENT_STATE_PASSED("审核通过"),

    /**
     * 审核驳回：请按照驳回原因修改申请资料，并更换业务申请编码，重新提交申请。
     */
    APPLYMENT_STATE_REJECTED("审核驳回"),

    /**
     * 已冻结：可能是该主体已完成过入驻，请查看驳回原因，并通知驳回原因中指定的联系人扫描微信支付返回的小程序码在小程序端完成授权流程。
     */
    APPLYMENT_STATE_FREEZED("已冻结"),

    /**
     * 已作废：表示申请单已被撤销，无需再对其进行操作。
     */
    APPLYMENT_STATE_CANCELED("已作废");


    private String desc;

    MchApplymentAuthorizeStateEnum(String desc) {
        this.desc = desc;
    }

    public String getDesc() {
        return desc;
    }

    public static boolean isFinalState(String state) {
        if (APPLYMENT_STATE_PASSED.name().equals(state) ||
                APPLYMENT_STATE_FREEZED.name().equals(state) ||
                APPLYMENT_STATE_CANCELED.name().equals(state)) {
            return true;
        }
        return false;
    }

    public static boolean isCanApply(String state) {
        if (APPLYMENT_STATE_EDITTING.name().equals(state) || APPLYMENT_STATE_REJECTED.name().equals(state)) {
            return true;
        }
        return false;
    }

    public static String getDescByName(String state) {
        for (MchApplymentAuthorizeStateEnum mchApplymentAuthorizeStateEnum : MchApplymentAuthorizeStateEnum.values()) {
            if (mchApplymentAuthorizeStateEnum.name().equals(state)) {
                return mchApplymentAuthorizeStateEnum.getDesc();
            }
        }
        return null;
    }

}
