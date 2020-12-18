package com.github.sung.wxpay.v3.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxApplymentSubV3Result extends BaseWxPayV3Result {
    private static final long serialVersionUID = 1077154716465200038L;

    /**
     * 微信支付申请单号
     * applyment_id
     * uint64
     * 是
     */
    @SerializedName("applyment_id")
    private String applymentId;


}