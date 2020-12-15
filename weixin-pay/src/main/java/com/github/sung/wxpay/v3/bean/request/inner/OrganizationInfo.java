package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 组织机构代码证
 * 条件选填	主体为企业/党政、机关及事业单位/其他组织，且证件号码不是18位时必填。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationInfo extends BaseV3Inner {
    private static final long serialVersionUID = 2865604284651620832L;


    /**
     * 组织机构代码证照片
     * organization_copy
     * string(256)
     * 是
     */
    @Required
    @SerializedName("organization_copy")
    private String organizationCopy;

    /**
     * 组织机构代码
     * organization_code
     * string(32)
     * 是
     */
    @Required
    @SerializedName("organization_code")
    private String organizationCode;

    /**
     * 组织机构代码证有效期开始日期
     * org_period_begin
     * string(128)
     * 是
     */
    @Required
    @SerializedName("org_period_begin")
    private String orgPeriodBegin;

    /**
     * 组织机构代码证有效期结束日期
     * org_period_end
     * string(128)
     * 是
     */
    @Required
    @SerializedName("org_period_end")
    private String orgPeriodEnd;

    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
