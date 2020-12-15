package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 登记证书
 * 条件选填 主体为党政、机关及事业单位/其他组织，必填。
 * 1、党政、机关及事业单位：请上传相关部门颁发的证书，如：事业单位法人证书、统一社会信用代码证书。
 * 2、其他组织：请上传相关部门颁发的证书，如：社会团体法人登记证书、民办非企业单位登记证书、基金会法人登记证书。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class CertificateInfo extends BaseV3Inner {
    private static final long serialVersionUID = 8767879736448835703L;

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
     * 商户名称
     * merchant_name
     * string(128)
     * 是
     */
    @Required
    @SerializedName("merchant_name")
    private String merchantName;

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
     * 法人姓名
     * legal_person
     * string(64)
     */
    @Required
    @SerializedName("legal_person")
    private String legalPerson;

    /**
     * 有效期限开始日期
     * period_begin
     * string(128)
     * 是
     */
    @Required
    @SerializedName("period_begin")
    private String periodBegin;

    /**
     * 有效期限结束日期
     * period_end
     * string(128)
     * 是
     */
    @Required
    @SerializedName("period_end")
    private String periodEnd;


    @Override
    public void checkConstraints() throws WxErrorException {
    }
}
