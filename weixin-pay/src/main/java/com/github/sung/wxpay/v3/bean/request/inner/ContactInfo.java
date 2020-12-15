package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


/**
 * 超级管理员信息
 * 超级管理员需在开户后进行签约，并接收日常重要管理信息和进行资金操作，请确定其为商户法定代表人或负责人。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfo extends BaseV3Inner {
    private static final long serialVersionUID = -8538158677810051141L;

    /**
     * 超级管理员姓名
     * contact_name
     * string(2048)
     * 是
     */
    @Required
    @SerializedName("contact_name")
    private String contactName;

    /**
     * 超级管理员身份证件号码
     * contact_id_number
     * string(2048)
     */
    @SerializedName("contact_id_number")
    private String contactIdNumber;

    /**
     * 超级管理员微信openid
     * openid
     * string(128)
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 联系手机
     * mobile_phone
     * string(2048)
     * 是
     */
    @Required
    @SerializedName("mobile_phone")
    private String mobilePhone;


    /**
     * 联系邮箱
     * contact_email
     * string(2048)
     * 是
     */
    @Required
    @SerializedName("contact_email")
    private String contactEmail;


    @Override
    public void checkConstraints() throws WxErrorException {
        if (StringUtils.isBlank(this.contactIdNumber) == StringUtils.isBlank(this.openid)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "超级管理员身份证号码 与 超级管理员微信openid，二选一必填");
        }

    }
}
