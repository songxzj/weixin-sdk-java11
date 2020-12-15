package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxpay.v3.enums.AuthorizeSubjectTypeEnum;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * 主体信息
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeSubjectInfo extends BaseV3Inner {
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
     * 营业执照信息
     * business_licence_info
     * object
     */
    @SerializedName("business_licence_info")
    private AuthorizeBusinessLicenceInfo businessLicenceInfo;

    /**
     * 登记证书
     * certificate_info
     * object
     */
    @SerializedName("certificate_info")
    private AuthorizeCertificateInfo certificateInfo;

    /**
     * 单位证明函照片
     * company_prove_copy
     * string(255)
     * 否
     */
    @SerializedName("company_prove_copy")
    private String companyProveCopy;

    /**
     * 辅助证明材料信息
     * assist_prove_info
     * object
     * 否
     */
    @SerializedName("assist_prove_info")
    private AuthorizeAssistProveInfo assistProveInfo;

    /**
     * 经营许可证信息
     * special_operation_info
     * array
     * 否
     */
    @SerializedName("special_operation_info")
    private List<AuthorizeSpecialOperationInfo> specialOperationInfo;


    @Override
    public void checkConstraints() throws WxErrorException {
        if ((AuthorizeSubjectTypeEnum.SUBJECT_TYPE_INDIVIDUAL.name().equals(this.subjectType) || AuthorizeSubjectTypeEnum.SUBJECT_TYPE_ENTERPRISE.name().equals(this.subjectType)) && this.businessLicenceInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体类型为企业或个体户时，营业执照信息必填。");
        }
        if ((AuthorizeSubjectTypeEnum.SUBJECT_TYPE_INSTITUTIONS_CLONED.name().equals(this.subjectType) || AuthorizeSubjectTypeEnum.SUBJECT_TYPE_OTHERS.name().equals(this.subjectType)) && this.certificateInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体类型为事业单位或其他组织时，登记证书信息必填。");
        }
        if (AuthorizeSubjectTypeEnum.SUBJECT_TYPE_INSTITUTIONS_CLONED.name().equals(this.subjectType) && StringUtils.isBlank(this.companyProveCopy)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体类型为事业单位时，单位证明函照片必填。");
        }
        if (AuthorizeSubjectTypeEnum.SUBJECT_TYPE_MICRO.name().equals(this.subjectType) && this.assistProveInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "主体类型为小微商户时，辅助证明材料信息必填。");
        }
        if (this.businessLicenceInfo != null) {
            this.businessLicenceInfo.checkConstraints();
        }
        if (this.certificateInfo != null) {
            this.certificateInfo.checkConstraints();
        }
        if (this.assistProveInfo != null) {
            this.assistProveInfo.checkConstraints();
        }
        if (this.specialOperationInfo != null) {
            for (AuthorizeSpecialOperationInfo authorizeSpecialOperationInfo : this.specialOperationInfo) {
                authorizeSpecialOperationInfo.checkConstraints();
            }
        }
    }


}
