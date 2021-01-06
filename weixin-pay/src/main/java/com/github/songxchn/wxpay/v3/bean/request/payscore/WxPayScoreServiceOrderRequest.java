package com.github.songxchn.wxpay.v3.bean.request.payscore;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.songxchn.wxpay.v3.bean.result.payscore.WxPayScoreServiceOrderResult;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.List;

/**
 * todo 支付分
 * version:2020.03.05
 * 创建支付分订单API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter6_1_14.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxPayScoreServiceOrderRequest extends BaseWxPayV3Request<WxPayScoreServiceOrderResult> {
    private static final long serialVersionUID = -2778638176133803381L;
    /**
     * 商户服务订单号
     * out_order_no
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("out_order_no")
    private String outOrderNo;

    /**
     * 应用ID
     * appid
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("appid")
    private String appid;

    /**
     * 服务ID
     * service_id
     * string[1,32]
     * 是
     */
    @Required
    @SerializedName("service_id")
    private String serviceId;

    /**
     * 服务信息
     * service_introduction
     * string[1,20]
     * 是
     */
    @Required
    @SerializedName("service_introduction")
    private String serviceIntroduction;

    /**
     * 后付费项目
     * post_payments
     * array
     * 否
     */
    @SerializedName("post_payments")
    private List<PostPayment> postPayments;

    /**
     * 后付费商户优惠
     * post_discounts
     * array
     * 否
     */
    @SerializedName("post_discounts")
    private List<PostDiscount> postDiscounts;

    /**
     * 服务时间段
     * time_range
     * object
     * 是
     */
    @Required
    @SerializedName("time_range")
    private TimeRange timeRange;

    /**
     * 服务位置
     * location
     * object
     * 否
     */
    @SerializedName("location")
    private Location location;

    /**
     * 订单风险金
     * risk_fund
     * object
     * 是
     */
    @Required
    @SerializedName("risk_fund")
    private RiskFund riskFund;

    /**
     * 商户数据包	attach
     * string[1,256]
     * 否
     */
    @SerializedName("attach")
    private String attach;

    /**
     * 商户回调地址
     * notify_url
     * string[1,255]
     * 是
     */
    @Required
    @SerializedName("notify_url")
    private String notifyUrl;

    /**
     * 用户标识
     * openid
     * string[1,128]
     * 条件选填
     */
    @SerializedName("openid")
    private String openid;

    /**
     * 是否需要用户确认
     * need_user_confirm
     * bool
     * 否
     */
    @SerializedName("need_user_confirm")
    private Boolean needUserConfirm;

    @Override
    public String routing() {
        return null;
    }

    @Override
    public Class<WxPayScoreServiceOrderResult> getResultClass() {
        return null;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return null;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {

    }

    /**
     * 后付费项目
     */
    @Data
    @NoArgsConstructor
    public static class PostPayment implements Serializable {
        private static final long serialVersionUID = -4694931039858907062L;

        /**
         * 付费项目名称
         * name
         * string[1,20]
         * 否
         */
        @SerializedName("name")
        private String name;

        /**
         * 金额
         * amount
         * int64
         * 条件选填
         */
        @SerializedName("amount")
        private Integer amount;

        /**
         * 计费说明
         * description
         * string[1,30]
         * 条件选填
         */
        @SerializedName("description")
        private String description;

        /**
         * 付费数量
         * count
         * uint32
         * 否
         */
        @SerializedName("count")
        private Integer count;
    }

    /**
     * 后付费商户优惠
     */
    @Data
    @NoArgsConstructor
    public static class PostDiscount implements Serializable {
        private static final long serialVersionUID = -1235417652093452035L;

        /**
         * 优惠名称
         * name
         * string[1,20]
         * 条件选填
         */
        @SerializedName("name")
        private String name;

        /**
         * 优惠说明
         * description
         * string[1,30]
         * 条件选填
         */
        @SerializedName("description")
        private String description;

        /**
         * 优惠数量
         * count
         * uint32
         * 否
         */
        @SerializedName("count")
        private Integer count;
    }

    /**
     * 服务时间段
     */
    @Data
    @NoArgsConstructor
    public static class TimeRange implements Serializable {
        private static final long serialVersionUID = -4530028594283254202L;
        /**
         * 服务开始时间
         * start_time
         * string[1,14]
         * 是
         */
        @SerializedName("start_time")
        private String startTime;

        /**
         * 服务开始时间备注
         * start_time_remark
         * string[1,20]
         * 否
         */
        @SerializedName("start_time_remark")
        private String startTimeRemark;

        /**
         * 预计服务结束时间
         * end_time
         * string[1,14]
         * 否
         */
        @SerializedName("end_time")
        private String endTime;

        /**
         * 预计服务结束时间备注
         * end_time_remark
         * string[1,20]
         * 否
         */
        @SerializedName("end_time_remark")
        private String endTimeRemark;
    }

    /**
     * 服务位置
     */
    @Data
    @NoArgsConstructor
    public static class Location implements Serializable {
        private static final long serialVersionUID = 275418943155817014L;

        /**
         * 服务开始地点
         * start_location
         * string[1,50]
         * 否
         */
        @SerializedName("start_location")
        private String startLocation;

        /**
         * 预计服务结束位置
         * end_location
         * string[1,50]
         * 条件选填
         */
        @SerializedName("end_location")
        private String endLocation;
    }

    /**
     * 订单风险金
     */
    @Data
    @NoArgsConstructor
    public static class RiskFund implements Serializable {
        private static final long serialVersionUID = 5853198509551872666L;

        /**
         * 风险金名称
         * name
         * string[1,64]
         * 是
         */
        @SerializedName("name")
        private String name;

        /**
         * 风险金额
         * amount
         * int64
         * 是
         */
        @SerializedName("amount")
        private int amount;

        /**
         * 风险说明
         * description
         * string[1,30]
         * 否
         */
        @SerializedName("description")
        private String description;
    }


}
