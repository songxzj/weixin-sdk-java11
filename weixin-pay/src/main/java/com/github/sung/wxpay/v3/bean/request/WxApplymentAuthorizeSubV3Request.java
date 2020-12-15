package com.github.sung.wxpay.v3.bean.request;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxpay.v3.bean.request.inner.AuthorizeContactInfo;
import com.github.sung.wxpay.v3.bean.request.inner.AuthorizeIdentificationInfo;
import com.github.sung.wxpay.v3.bean.request.inner.AuthorizeSubjectInfo;
import com.github.sung.wxpay.v3.bean.result.WxApplymentAuthorizeSubV3Result;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 商户开户意愿提交申请单API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/applysubject/chapter5_1.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxApplymentAuthorizeSubV3Request extends BaseWxPayV3Request<WxApplymentAuthorizeSubV3Result> {
    private static final long serialVersionUID = -1102379109688853899L;


    /**
     * 渠道商户号
     * channel_id
     * string(9)
     * 否
     */
    @SerializedName("channel_id")
    private String channelId;

    /**
     * 业务申请编号
     * business_code
     * string(128)
     * 是
     */
    @Required
    @SerializedName("business_code")
    private String businessCode;

    /**
     * 联系人信息
     * contact_info
     * object
     * 是
     */
    @Required
    @SerializedName("contact_info")
    private AuthorizeContactInfo contactInfo;

    /**
     * 主体信息
     * subject_info
     * object
     * 是
     */
    @Required
    @SerializedName("subject_info")
    private AuthorizeSubjectInfo subjectInfo;

    /**
     * 法人身份信息
     * identification_info
     * object
     * 是
     */
    @Required
    @SerializedName("identification_info")
    private AuthorizeIdentificationInfo identificationInfo;


    @Override
    public String routing() {
        return "/v3/apply4subject/applyment";
    }

    @Override
    public Class<WxApplymentAuthorizeSubV3Result> getResultClass() {
        return WxApplymentAuthorizeSubV3Result.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        if (this.contactInfo != null) {
            this.contactInfo.checkConstraints();
        }
        if (this.subjectInfo != null) {
            this.subjectInfo.checkConstraints();
        }
        if (this.identificationInfo != null) {
            this.identificationInfo.checkConstraints();
        }
    }

}
