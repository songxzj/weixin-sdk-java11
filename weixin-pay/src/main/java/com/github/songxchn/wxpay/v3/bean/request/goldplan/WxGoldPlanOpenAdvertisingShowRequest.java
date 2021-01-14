package com.github.songxchn.wxpay.v3.bean.request.goldplan;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.songxchn.wxpay.v3.bean.result.goldplan.WxGoldPlanOpenAdvertisingShowResult;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

import java.util.List;

/**
 * version:2020.08.27
 * 开通广告展示API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/goldplan/chapter3_4.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxGoldPlanOpenAdvertisingShowRequest extends BaseWxPayV3Request<WxGoldPlanOpenAdvertisingShowResult> {
    private static final long serialVersionUID = -3915883696622475413L;


    /**
     * 特约商户号
     * sub_mchid
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("sub_mchid")
    private String subMchid;

    /**
     * 操作类型
     * operation_type
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("advertising_industry_filters")
    private List<String> advertisingIndustryFilters;

    @Override
    public String routing() {
        return "/v3/goldplan/merchants/open-advertising-show";
    }

    @Override
    public Class<WxGoldPlanOpenAdvertisingShowResult> getResultClass() {
        return WxGoldPlanOpenAdvertisingShowResult.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.PATCH;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        int size = this.advertisingIndustryFilters.size();
        if (!(size > 0 && size < 4)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "同业过滤标签最少传一个，最多三个");
        }
    }
}
