package com.github.sung.wxpay.v3.bean.request.applyment;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.sung.wxpay.v3.bean.result.applyment.WxApplymentAuthorizeSubStateV3Result;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 获取商户开户意愿查询申请单审核结果API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/applysubject/chapter5_3.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxApplymentAuthorizeSubStateV3Request extends BaseWxPayV3Request<WxApplymentAuthorizeSubStateV3Result> {
    private static final long serialVersionUID = -4629107064469064525L;


    /**
     * 业务申请编号
     * business_code
     * string(128)
     * 是
     */
    @Required
    @SerializedName("business_code")
    private String businessCode;

    @Override
    public String routing() {
        return "/v3/apply4subject/applyment?business_code=" + this.businessCode;
    }

    @Override
    public Class<WxApplymentAuthorizeSubStateV3Result> getResultClass() {
        return WxApplymentAuthorizeSubStateV3Result.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
    }
}
