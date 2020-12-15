package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 法人身份信息
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeIdentificationInfo extends BaseV3Inner {
    private static final long serialVersionUID = -4775534942928369954L;


    /**
     * 法人证件类型
     * identification_type
     * enum
     * 是
     */
    @Required
    @SerializedName("identification_type")
    private String identificationType;

    /**
     * 证件姓名
     * identification_name
     * string(1024)
     * 是
     */
    @Required
    @SerializedName("identification_name")
    private String identificationName;

    /**
     * 证件号码
     * identification_number
     * string(1024)
     * 是
     */
    @Required
    @SerializedName("identification_number")
    private String identificationNumber;

    /**
     * 证件有效日期
     * identification_valid_date
     * string(64)
     * 是
     */
    @Required
    @SerializedName("identification_valid_date")
    private String identificationValidDate;

    /**
     * 证件正面照片
     * identification_front_copy
     * string(255)
     * 是
     */
    @Required
    @SerializedName("identification_front_copy")
    private String identificationFrontCopy;

    /**
     * 证件反面照片
     * identification_back_copy
     * string(255)
     * 否
     */
    @SerializedName("identification_back_copy")
    private String identificationBackCopy;



    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
