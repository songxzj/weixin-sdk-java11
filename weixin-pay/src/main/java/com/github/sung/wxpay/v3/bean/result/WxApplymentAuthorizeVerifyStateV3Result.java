package com.github.sung.wxpay.v3.bean.result;


import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxApplymentAuthorizeVerifyStateV3Result extends BaseWxPayV3Result {
    private static final long serialVersionUID = 1473687025896489163L;


    /**
     * 授权状态
     * authorize_state	string(32)
     * 是
     */
    @SerializedName("authorize_state")
    private String authorizeState;
}
