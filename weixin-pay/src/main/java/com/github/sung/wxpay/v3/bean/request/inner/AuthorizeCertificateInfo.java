package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 登记证书信息
 * 主体类型为事业单位或其他组织时，登记证书信息必填。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeCertificateInfo extends BaseV3Inner {
    private static final long serialVersionUID = 5640747094415392402L;


    /**
     * 登记证书类型
     * cert_type
     * enum
     * 是
     */
    @Required
    @SerializedName("cert_type")
    private String certType;

    /**
     * 证书号
     * cert_number
     * string(32)
     * 是
     */
    @Required
    @SerializedName("cert_number")
    private String certNumber;

    /**
     * 登记证书照片
     * cert_copy
     * string(255)
     * 是
     */
    @Required
    @SerializedName("cert_copy")
    private String certCopy;

    /**
     * 商户名称
     * merchant_name
     * string(128)
     * 是
     */
    @Required
    @SerializedName("merchant_name")
    private String merchantName;

    /**
     * 法人姓名
     * legal_person
     * string(64)
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
     * 证书有效日期
     * cert_valid_date
     * string(128)
     * 是
     */
    @Required
    @SerializedName("cert_valid_date")
    private String certValidDate;


    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
