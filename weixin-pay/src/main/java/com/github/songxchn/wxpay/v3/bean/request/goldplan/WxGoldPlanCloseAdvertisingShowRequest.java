package com.github.songxchn.wxpay.v3.bean.request.goldplan;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.songxchn.wxpay.v3.bean.result.goldplan.WxGoldPlanCloseAdvertisingShowResult;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * version:2020.08.27
 * 关闭广告展示API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/goldplan/chapter3_5.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxGoldPlanCloseAdvertisingShowRequest extends BaseWxPayV3Request<WxGoldPlanCloseAdvertisingShowResult> {
    private static final long serialVersionUID = -6293598471995348193L;

    /**
     * 特约商户号
     * sub_mchid
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("sub_mchid")
    private String subMchid;



    @Override
    public String routing() {
        return "/v3/goldplan/merchants/close-advertising-show";
    }

    @Override
    public Class<WxGoldPlanCloseAdvertisingShowResult> getResultClass() {
        return WxGoldPlanCloseAdvertisingShowResult.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
    }
}
