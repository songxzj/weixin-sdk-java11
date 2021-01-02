package com.github.songxchn.wxpay.v3.enums;


/**
 * 证件类型
 */
public enum IDDocTypeEnum {

    /**
     * 中国大陆居民-身份证
     */
    IDENTIFICATION_TYPE_IDCARD(1L),

    /**
     * 其他国家或地区居民-护照
     */
    IDENTIFICATION_TYPE_OVERSEA_PASSPORT(2L),

    /**
     * 中国香港居民-来往内地通行证
     */
    IDENTIFICATION_TYPE_HONGKONG_PASSPORT(3L),

    /**
     * 中国澳门居民-来往内地通行证
     */
    IDENTIFICATION_TYPE_MACAO_PASSPORT(4L),

    /**
     * 中国台湾居民-来往大陆通行证
     */
    IDENTIFICATION_TYPE_TAIWAN_PASSPORT(5L);

    private Long code;

    IDDocTypeEnum(Long code) {
        this.code = code;
    }

    public static String getByCode(Long code) {
        for (IDDocTypeEnum idDocTypeEnum : IDDocTypeEnum.values()) {
            if (idDocTypeEnum.code.equals(code)) {
                return idDocTypeEnum.name();
            }
        }
        return null;
    }
}
