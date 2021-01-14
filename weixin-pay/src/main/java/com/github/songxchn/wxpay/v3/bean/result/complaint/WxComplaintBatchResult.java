package com.github.songxchn.wxpay.v3.bean.result.complaint;

import com.github.songxchn.common.annotation.SensitiveEncrypt;
import com.github.songxchn.wxpay.v3.bean.result.BaseWxPayV3Result;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WxComplaintBatchResult extends BaseWxPayV3Result {
    private static final long serialVersionUID = 6009014042667751249L;

    /**
     * 用户投诉信息详情
     * data
     * array
     * 是
     */
    @SerializedName("data")
    private List<Complaint> data;

    /**
     * 分页开始位置
     * offset
     * uint32
     * 是
     */
    @SerializedName("offset")
    private Integer offset;

    /**
     * 分页大小
     * limit
     * uint32
     * 是
     */
    @SerializedName("limit")
    private Integer limit;

    /**
     * 投诉总条数
     * total_count
     * uint64
     * 否
     */
    @SerializedName("total_count")
    private Integer totalCount;

    /**
     * 用户投诉信息
     */
    @Data
    @NoArgsConstructor
    public static class Complaint implements Serializable {
        private static final long serialVersionUID = 2371174843188337417L;


        /**
         * 商户订单号
         * out_trade_no
         * string[1,64]
         * 是
         */
        @SerializedName("out_trade_no")
        private String outTradeNo;

        /**
         * 投诉时间
         * complaint_time
         * string[1,32]
         * 是
         */
        @SerializedName("complaint_time")
        private String complaintTime;

        /**
         * 投诉金额
         * amount
         * uint64
         * 是
         */
        @SerializedName("amount")
        private Integer amount;

        /**
         * 投诉人联系方式
         * payer_phone
         * string[1,256]
         * 否
         */
        @SensitiveEncrypt
        @SerializedName("payer_phone")
        private String payerPhone;

        /**
         * 投诉描述
         * complaint_detail
         * string[1,300]
         * 是
         */
        @SerializedName("complaint_detail")
        private String complaintDetail;

        /**
         * 投诉单状态
         * complaint_state
         * string[1,30]
         * 否
         */
        @SerializedName("complaint_state")
        private String complaintState;

        /**
         * 微信订单号
         * transaction_id
         * string[1,64]
         * 是
         */
        @SerializedName("transaction_id")
        private String transactionId;

        /**
         * 冻结结束时间
         * frozen_end_time
         * string[1,32]
         * 否
         */
        @SerializedName("frozen_end_time")
        private String frozenEndTime;

        /**
         * 特约商户号
         * sub_mchid
         * string[1,64]
         * 否
         */
        @SerializedName("sub_mchid")
        private String subMchid;

        /**
         * 投诉单处理进展状态
         * complaint_handle_state
         * string[1, 64]
         * 是
         */
        @SerializedName("complaint_handle_state")
        private String complaintHandleState;

    }

}
