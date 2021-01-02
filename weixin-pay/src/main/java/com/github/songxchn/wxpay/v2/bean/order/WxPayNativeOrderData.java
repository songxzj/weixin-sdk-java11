package com.github.songxchn.wxpay.v2.bean.order;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Native支付
 */
@Data
@Builder
public class WxPayNativeOrderData implements Serializable {
    private static final long serialVersionUID = -8280926784905245526L;


    private String codeUrl;
}
