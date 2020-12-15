package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxpay.v3.enums.IDDocTypeEnum;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;


/**
 * 经营者/法人身份证件
 * 1、个体户：请上传经营者的身份证件。
 * 2、企业/党政、机关及事业单位/其他组织：请上传法人的身份证件。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class IdentityInfo extends BaseV3Inner {
    private static final long serialVersionUID = -985545099597198929L;


    /**
     * 证件类型
     * id_doc_type
     * enum
     * 是
     */
    @Required
    @SerializedName("id_doc_type")
    private String idDocType;

    /**
     * 身份证信息
     * id_card_info
     * object
     * 否
     */
    @SerializedName("id_card_info")
    private IDCardInfo idCardInfo;

    /**
     * 其他类型证件信息
     * id_doc_info
     * object
     */
    @SerializedName("id_doc_info")
    private IDDocInfo idDocInfo;

    /**
     * 经营者/法人是否为受益人
     * owner
     * bool
     * 是
     */
    @Required
    @SerializedName("owner")
    private Boolean owner;


    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IDCardInfo extends BaseV3Inner {
        private static final long serialVersionUID = -4309990580461697098L;

        /**
         * 身份证人像面照片
         * id_card_copy
         * string(256)
         * 是
         */
        @Required
        @SerializedName("id_card_copy")
        private String idCardCopy;

        /**
         * 身份证国徽面照片
         * id_card_national
         * string(256)
         * 是
         */
        @Required
        @SerializedName("id_card_national")
        private String idCardNational;

        /**
         * 身份证姓名
         * id_card_name
         * string(256)
         * 是
         */
        @Required
        @SerializedName("id_card_name")
        private String idCardName;


        /**
         * 身份证号码
         * id_card_number
         * string(256)
         * 是
         */
        @Required
        @SerializedName("id_card_number")
        private String idCardNumber;

        /**
         * 身份证有效期开始时间
         * card_period_begin
         * string(128)
         * 是
         */
        @Required
        @SerializedName("card_period_begin")
        private String cardPeriodBegin;

        /**
         * 结束时间
         * card_period_end
         * string(128)
         * 是
         */
        @Required
        @SerializedName("card_period_end")
        private String cardPeriodEnd;

        @Override
        public void checkConstraints() throws WxErrorException {
        }
    }


    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class IDDocInfo extends BaseV3Inner {
        private static final long serialVersionUID = 6214364176836312673L;

        /**
         * 证件照片
         * id_doc_copy
         * string(256)
         * 是
         */
        @Required
        @SerializedName("id_doc_copy")
        private String idDocCopy;

        /**
         * 证件姓名
         * id_doc_name
         * string(128)
         * 是
         */
        @Required
        @SerializedName("id_doc_name")
        private String idDocName;

        /**
         * 证件号码
         * id_doc_number
         * string(128)
         * 是
         */
        @Required
        @SerializedName("id_doc_number")
        private String idDocNumber;


        /**
         * 证件有效期开始时间
         * doc_period_begin
         * string(128)
         * 是
         */
        @Required
        @SerializedName("doc_period_begin")
        private String docPeriodBegin;

        /**
         * 证件有效期结束时间
         * doc_period_end
         * string(128)
         * 是
         */
        @Required
        @SerializedName("doc_period_end")
        private String docPeriodEnd;

        @Override
        public void checkConstraints() throws WxErrorException {
        }
    }

    @Override
    public void checkConstraints() throws WxErrorException {
        if (IDDocTypeEnum.IDENTIFICATION_TYPE_IDCARD.name().equals(this.idDocType) && this.idCardInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "证件类型为“身份证”时 身份证信息必填");
        }
        if (!IDDocTypeEnum.IDENTIFICATION_TYPE_IDCARD.name().equals(this.idDocType) && this.idDocInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "证件类型为“来往内地通行证、来往大陆通行证、护照”时 其他类型证件信息必填");
        }
        if (this.idCardInfo != null) {
            this.idCardInfo.checkConstraints();
        }
        if (this.idDocInfo != null) {
            this.idDocInfo.checkConstraints();
        }

    }
}
