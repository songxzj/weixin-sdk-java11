package com.github.songxchn.wxpay.v2.bean.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Mweb支付
 */
@Data
@Builder
public class WxPayMwebOrderData implements Serializable {
    private static final long serialVersionUID = -7427427635279463172L;

    private String mwebUrl;
}
