package com.github.sung.wxpay.v3.bean.request;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxpay.v3.bean.request.inner.*;
import com.github.sung.wxpay.v3.bean.result.WxApplymentSubV3Result;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * 提交申请单API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/applyment4sub/chapter3_1.shtml">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxApplymentSubV3Request extends BaseWxPayV3Request<WxApplymentSubV3Result> {
    private static final long serialVersionUID = -1025236917297379236L;

    /**
     * 业务申请编号
     * business_code
     * string(124)
     * 是
     */
    @Required
    @SerializedName("business_code")
    private String businessCode;

    /**
     * 超级管理员信息
     * contact_info
     * object
     * 是
     */
    @Required
    @SerializedName("contact_info")
    private ContactInfo contactInfo;

    /**
     * 主体资料
     * subject_info	object
     * 是
     */
    @Required
    @SerializedName("subject_info")
    private SubjectInfo subjectInfo;

    /**
     * 经营资料
     * business_info
     * object
     * 是
     */
    @Required
    @SerializedName("business_info")
    private BusinessInfo businessInfo;

    /**
     * 结算规则
     * settlement_info
     * object
     * 是
     */
    @Required
    @SerializedName("settlement_info")
    private SettlementInfo settlementInfo;

    /**
     * 结算银行账户
     * bank_account_info
     * object
     */
    @SerializedName("bank_account_info")
    private BankAccountInfo bankAccountInfo;

    /**
     * 补充材料
     * addition_info
     * object
     * 否
     */
    @SerializedName("addition_info")
    private AdditionInfo additionInfo;


    @Override
    public String routing() {
        return "/v3/applyment4sub/applyment/";
    }

    @Override
    public Class<WxApplymentSubV3Result> getResultClass() {
        return WxApplymentSubV3Result.class;
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
        if (this.businessInfo != null) {
            this.businessInfo.checkConstraints();
        }
        if (this.settlementInfo != null) {
            this.settlementInfo.checkConstraints();
        }
        if (this.bankAccountInfo != null) {
            this.bankAccountInfo.checkConstraints();
        }
        if (this.additionInfo != null) {
            this.additionInfo.checkConstraints();
        }
    }
}
