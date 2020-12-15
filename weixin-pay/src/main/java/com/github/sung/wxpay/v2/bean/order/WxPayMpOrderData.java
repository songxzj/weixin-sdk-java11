package com.github.sung.wxpay.v2.bean.order;

import cn.hutool.core.util.RandomUtil;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * 公众号支付
 */
@Data
@Builder
public class WxPayMpOrderData implements Serializable {
    private static final long serialVersionUID = -1596569775320792912L;

    private String appId;

    @Builder.Default
    private String timeStamp = String.valueOf(System.currentTimeMillis() / 1000);

    @Builder.Default
    private String nonceStr = RandomUtil.randomString(32);
    /**
     * 由于package为java保留关键字，因此改为packageValue. 前端使用时记得要更改为package
     */
    @XStreamAlias("package")
    private String packageValue;
    private String signType;
    private String paySign;

}
