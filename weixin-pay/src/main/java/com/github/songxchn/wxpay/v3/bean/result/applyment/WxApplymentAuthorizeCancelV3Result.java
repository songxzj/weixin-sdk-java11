package com.github.songxchn.wxpay.v3.bean.result.applyment;


import com.github.songxchn.wxpay.v3.bean.result.BaseWxPayV3Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxApplymentAuthorizeCancelV3Result extends BaseWxPayV3Result {
    private static final long serialVersionUID = -1496053177451985450L;
}
