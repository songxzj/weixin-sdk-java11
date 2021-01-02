package com.github.songxchn.wxpay.v3.enums;

/**
 * 售卖商品/提供服务描述
 */
public enum ProductDescEnum {

    餐饮(1L),

    线下零售(2L),

    居民生活服务(3L),

    休闲娱乐(4L),

    交通出行(5L),

    其他(6L);

    private Long code;

    ProductDescEnum(Long code) {
        this.code = code;
    }

    public static String getByCode(Long code) {
        for (ProductDescEnum productDescEnum : ProductDescEnum.values()) {
            if (productDescEnum.code.equals(code)) {
                return productDescEnum.name();
            }
        }
        return ProductDescEnum.其他.name();
    }



}
