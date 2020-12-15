package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxpay.v3.enums.SubjectTypeEnum;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

/**
 * 主体资料
 * 请填写商家的营业执照/登记证书、经营者/法人的证件等信息。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class SubjectInfo extends BaseV3Inner {
    private static final long serialVersionUID = 4142293335799954435L;

    /**
     * 主体类型
     * subject_type
     * string(32)
     * 是
     */
    @Required
    @SerializedName("subject_type")
    private String subjectType;

    /**
     * 营业执照
     * business_license_info
     * object
     */
    @SerializedName("business_license_info")
    private BusinessLicenseInfo businessLicenseInfo;

    /**
     * 登记证书
     * certificate_info
     * object
     */
    @SerializedName("certificate_info")
    private CertificateInfo certificateInfo;

    /**
     * 组织机构代码证
     * organization_info
     * object
     */
    @SerializedName("organization_info")
    private OrganizationInfo organizationInfo;

    /**
     * 单位证明函照片
     * certificate_letter_copy
     * string(255)
     */
    @SerializedName("certificate_letter_copy")
    private String certificateLetterCopy;


    /**
     * 经营者/法人身份证件
     * identity_info
     * object
     */
    @Required
    @SerializedName("identity_info")
    private IdentityInfo identityInfo;


    /**
     * 最终受益人信息(UBO)
     * ubo_info
     * object
     */
    @SerializedName("ubo_info")
    private UboInfo uboInfo;


    @Override
    public void checkConstraints() throws WxErrorException {
        if ((SubjectTypeEnum.SUBJECT_TYPE_INDIVIDUAL.name().equals(this.subjectType) || SubjectTypeEnum.SUBJECT_TYPE_ENTERPRISE.name().equals(this.subjectType)) && this.businessLicenseInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体为个体户/企业，business_license_info 必填");
        }
        if ((SubjectTypeEnum.SUBJECT_TYPE_INSTITUTIONS.name().equals(this.subjectType) || SubjectTypeEnum.SUBJECT_TYPE_OTHERS.name().equals(this.subjectType)) && this.certificateInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体为党政、机关及事业单位/其他组织，certificate_info 必填");
        }
        if (SubjectTypeEnum.SUBJECT_TYPE_INSTITUTIONS.name().equals(this.subjectType) && StringUtils.isBlank(this.certificateLetterCopy)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体类型为党政、机关及事业单位，certificate_letter_copy 必填");
        }
        if (!this.identityInfo.getOwner() && this.uboInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "若经营者/法人不是最终受益所有人，则需提填写受益所有人信息");
        }
        if (this.businessLicenseInfo != null) {
            this.businessLicenseInfo.checkConstraints();
        }
        if (this.certificateInfo != null) {
            this.certificateInfo.checkConstraints();
        }
        if (this.organizationInfo != null) {
            this.organizationInfo.checkConstraints();
        }
        if (this.identityInfo != null) {
            this.identityInfo.checkConstraints();
        }
        if (this.uboInfo != null) {
            this.uboInfo.checkConstraints();
        }
    }

}
