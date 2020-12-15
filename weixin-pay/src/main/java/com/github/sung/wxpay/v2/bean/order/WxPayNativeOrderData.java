package com.github.sung.wxpay.v2.bean.order;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Native支付
 */
@Data
@Builder
public class WxPayNativeOrderData implements Serializable {
    private static final long serialVersionUID = -7427427635279463172L;

    private String codeUrl;
}
