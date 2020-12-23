package com.github.sung.wxpay.v3.bean.request.applyment;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.sung.wxpay.v3.bean.result.applyment.WxApplymentAuthorizeCancelV3Result;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 获取商户开户意愿撤销申请单API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/applysubject/chapter5_2.shtml">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxApplymentAuthorizeCancelV3Request extends BaseWxPayV3Request<WxApplymentAuthorizeCancelV3Result> {
    private static final long serialVersionUID = 8750355463940420185L;


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
        return "/v3/apply4subject/applyment/" + this.businessCode + "/cancel";
    }

    @Override
    public Class<WxApplymentAuthorizeCancelV3Result> getResultClass() {
        return WxApplymentAuthorizeCancelV3Result.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {

    }
}
