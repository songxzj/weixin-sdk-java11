package com.github.sung.wxpay.v3.enums;


/**
 * 主体类型
 */
public enum AuthorizeSubjectTypeEnum {

    /**
     * SUBJECT_TYPE_INDIVIDUAL（个体工商户）
     */
    SUBJECT_TYPE_INDIVIDUAL(4L),

    /**
     * SUBJECT_TYPE_ENTERPRISE（企业）
     */
    SUBJECT_TYPE_ENTERPRISE(2L),

    /**
     * SUBJECT_TYPE_INSTITUTIONS_CLONED（事业单位）
     */
    SUBJECT_TYPE_INSTITUTIONS_CLONED(3L),

    /**
     * SUBJECT_TYPE_OTHERS（其他组织）
     */
    SUBJECT_TYPE_OTHERS(1708L),

    /**
     * SUBJECT_TYPE_MICRO（小微商户）
     */
    SUBJECT_TYPE_MICRO(-1L);

    private Long code;

    AuthorizeSubjectTypeEnum(Long code) {
        this.code = code;
    }

    public static String getByCode(Long code) {
        for (AuthorizeSubjectTypeEnum subjectTypeEnum : AuthorizeSubjectTypeEnum.values()) {
            if (subjectTypeEnum.code.equals(code)) {
                return subjectTypeEnum.name();
            }
        }
        return null;
    }

}
