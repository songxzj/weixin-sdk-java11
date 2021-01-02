package com.github.songxchn.wxpay.v3.bean.result.bill;

import com.github.songxchn.wxpay.v3.bean.result.BaseWxPayV3Result;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxDownloadBillResult extends BaseWxPayV3Result {
    private static final long serialVersionUID = 1612105590999627154L;
}
