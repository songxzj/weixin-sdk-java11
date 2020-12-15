package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;


/**
 * 营业执照
 * 条件选填	1、主体为个体户/企业，必填。
 * 2、请上传“营业执照”，需年检章齐全，当年注册除外。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessLicenseInfo extends BaseV3Inner {
    private static final long serialVersionUID = -3499012665128533704L;

    /**
     * 营业执照照片
     * license_copy	string(255)
     * 是
     */
    @Required
    @SerializedName("license_copy")
    private String licenseCopy;

    /**
     * 注册号/统一社会信用代码
     * license_number
     * string(32)
     * 是
     */
    @Required
    @SerializedName("license_number")
    private String licenseNumber;

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
     * 个体户经营者/法人姓名
     * legal_person
     * string(64)
     * 是
     */
    @Required
    @SerializedName("legal_person")
    private String legalPerson;


    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
