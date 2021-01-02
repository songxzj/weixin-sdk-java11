package com.github.songxchn.wxpay.v2.bean.order;


import cn.hutool.core.util.RandomUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * APP支付
 */
@Data
@Builder
public class WxPayAppOrderData implements Serializable {
    private static final long serialVersionUID = -3997925111530175505L;

    private String appid;
    private String partnerid;
    private String prepayid;

    /**
     * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
     */
    @Builder.Default
    @XStreamAlias("package")
    private String packageValue = "Sign=WXPay";

    @Builder.Default
    private String noncestr  = RandomUtil.randomString(32);

    @Builder.Default
    private String timestamp = String.valueOf(System.currentTimeMillis() / 1000);

    private String sign;
}
