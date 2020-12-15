package com.github.sung.wxpay.v3.enums;

/**
 * 登记证书类型
 */
public enum CertificateTypeEnum {


    /**
     * 事业单位法人证书
     */
    CERTIFICATE_TYPE_2388(1L),

    /**
     * 统一社会信用代码证书
     */
    CERTIFICATE_TYPE_2389(2L),

    /**
     * 有偿服务许可证（军队医院适用）
     */
    CERTIFICATE_TYPE_2390(3L),

    /**
     * 医疗机构执业许可证（军队医院适用）
     */
    CERTIFICATE_TYPE_2391(4L),

    /**
     * 企业营业执照（挂靠企业的党组织适用）
     */
    CERTIFICATE_TYPE_2392(5L),

    /**
     * 组织机构代码证（政府机关适用）
     */
    CERTIFICATE_TYPE_2393(6L),

    /**
     * 社会团体法人登记证书
     */
    CERTIFICATE_TYPE_2394(7L),

    /**
     * 民办非企业单位登记证书
     */
    CERTIFICATE_TYPE_2395(8L),

    /**
     * 基金会法人登记证书
     */
    CERTIFICATE_TYPE_2396(9L),

    /**
     * 慈善组织公开募捐资格证书
     */
    CERTIFICATE_TYPE_2397(10L),

    /**
     * 农民专业合作社法人营业执照
     */
    CERTIFICATE_TYPE_2398(11L),

    /**
     * 宗教活动场所登记证
     */
    CERTIFICATE_TYPE_2399(12L),

    /**
     * 其他证书/批文/证明
     */
    CERTIFICATE_TYPE_2400(13L);

    private Long code;

    CertificateTypeEnum(Long code) {
        this.code = code;
    }


    public static String getByCode(Long code) {
        for (CertificateTypeEnum certificateTypeEnum : CertificateTypeEnum.values()) {
            if (certificateTypeEnum.code.equals(code)) {
                return certificateTypeEnum.name();
            }
        }
        return null;
    }
}
