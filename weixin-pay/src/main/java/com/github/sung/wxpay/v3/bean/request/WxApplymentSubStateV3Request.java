package com.github.sung.wxpay.v3.bean.request;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxpay.v3.bean.result.WxApplymentSubStateV3Result;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 查询申请单状态API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter3_2.shtml">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxApplymentSubStateV3Request  extends BaseWxPayV3Request<WxApplymentSubStateV3Result>{
    private static final long serialVersionUID = -7055710642500290503L;

    /**
     * 业务申请编号
     * business_code
     * string(124)
     * 是
     */
    @Required
    @SerializedName("business_code")
    private String businessCode;

    @Override
    public String routing() {
        return "/v3/applyment4sub/applyment/business_code/" + this.businessCode;
    }

    @Override
    public Class<WxApplymentSubStateV3Result> getResultClass() {
        return WxApplymentSubStateV3Result.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {

    }
}
