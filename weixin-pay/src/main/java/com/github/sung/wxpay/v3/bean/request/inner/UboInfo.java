package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxpay.v3.enums.IDDocTypeEnum;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.commons.lang3.StringUtils;


/**
 * 最终受益人信息(UBO)
 * 经营者/法人不是最终受益所有人，则需提填写受益所有人信息。
 * 根据国家相关法律法规，需要提供公司受益所有人信息，受益所有人需符合至少以下条件之一：
 * 1、直接或者间接拥有超过25%公司股权或者表决权的自然人。
 * 2、通过人事、财务等其他方式对公司进行控制的自然人。
 * 3、公司的高级管理人员，包括公司的经理、副经理、财务负责人、上市公司董事会秘书和公司章程规定的其他人员。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class UboInfo extends BaseV3Inner {
    private static final long serialVersionUID = 5110805206262783255L;


    /**
     * 证件类型
     * id_type
     * enum
     * 是
     */
    @Required
    @SerializedName("id_type")
    private String idType;

    /**
     * 身份证人像面照片
     * id_card_copy
     * string(256)
     */
    @SerializedName("id_card_copy")
    private String idCardCopy;


    /**
     * 身份证国徽面照片
     * id_card_national
     * string(256)
     */
    @SerializedName("id_card_national")
    private String idCardNational;


    /**
     * 证件照片
     * id_doc_copy
     * string(256)
     */
    @SerializedName("id_doc_copy")
    private String idDocCopy;

    /**
     * 受益人姓名
     * name
     * string
     * 是
     */
    @Required
    @SerializedName("name")
    private String name;


    /**
     * 证件号码
     * id_number
     * string(128)
     * 是
     */
    @Required
    @SerializedName("id_number")
    private String idNumber;


    /**
     * 证件有效期开始时间
     * id_period_begin
     * string(128)
     * 是
     */
    @Required
    @SerializedName("id_period_begin")
    private String idPeriodBegin;

    /**
     * 证件有效期结束时间
     * id_period_end
     * string(128)
     * 是
     */
    @Required
    @SerializedName("id_period_end")
    private String idPeriodEnd;


    @Override
    public void checkConstraints() throws WxErrorException {
        if (IDDocTypeEnum.IDENTIFICATION_TYPE_IDCARD.name().equals(this.idType) && StringUtils.isBlank(this.idCardCopy)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "受益人的证件类型为“身份证”时，上传身份证人像面照片");
        }
        if (IDDocTypeEnum.IDENTIFICATION_TYPE_IDCARD.name().equals(this.idType) && StringUtils.isBlank(this.idCardNational)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "受益人的证件类型为“身份证”时，上传身份证国徽面照片");
        }
        if (!IDDocTypeEnum.IDENTIFICATION_TYPE_IDCARD.name().equals(this.idType) && StringUtils.isBlank(this.idDocCopy)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "受益人的证件类型为“来往内地通行证、来往大陆通行证、护照”时，上传证件照片");
        }

    }
}
