package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 营业执照信息
 * 主体类型为企业或个体户时，营业执照信息必填
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeBusinessLicenceInfo extends BaseV3Inner {
    private static final long serialVersionUID = -5567222258379517260L;


    /**
     * 营业执照注册号
     * licence_number
     * string(32)
     * 是
     */
    @Required
    @SerializedName("licence_number")
    private String licenceNumber;

    /**
     * 营业执照照片
     * licence_copy
     * string(255)
     * 是
     */
    @Required
    @SerializedName("licence_copy")
    private String licenceCopy;

    /**
     * 商户名称
     * merchant_name
     * string(50)
     * 是
     */
    @Required
    @SerializedName("merchant_name")
    private String merchantName;

    /**
     * 法人姓名
     * legal_person
     * string(64)
     * 是
     */
    @Required
    @SerializedName("legal_person")
    private String legalPerson;

    /**
     * 注册地址
     * company_address
     * string(128)
     * 是
     */
    @Required
    @SerializedName("company_address")
    private String companyAddress;


    /**
     * 营业执照有效日期
     * licence_valid_date
     * string(64)
     * 是
     */
    @Required
    @SerializedName("licence_valid_date")
    private String licenceValidDate;


    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
