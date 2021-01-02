package com.github.songxchn.wxpay.v2.bean.result;

import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

import java.io.Serializable;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayRefundResult extends BaseWxPayResult {
    private static final long serialVersionUID = 6906394088343058862L;


    /**
     * <pre>
     * 微信订单号
     * transaction_id
     * 是
     * String(32)
     * 1217752501201407033233368018
     * 微信生成的订单号，在支付通知中有返回
     * </pre>
     **/
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * <pre>
     * 商户订单号
     * out_trade_no
     * 是
     * String(32)
     * 1217752501201407033233368018
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * </pre>
     **/
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 商户退款单号
     * out_refund_no
     * 是
     * String(64)
     * 1217752501201407033233368018
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     * </pre>
     **/
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * <pre>
     *  微信退款单号
     *  refund_id
     *  是
     *  String(32)
     *  1217752501201407033233368018
     *  微信退款单号
     * <pre>
     */
    @XStreamAlias("refund_id")
    private String refundId;

    /**
     * <pre>
     * 申请退款金额
     * refund_fee
     * 是
     * Int
     * 100
     * 退款总金额,单位为分,可以做部分退款
     * <pre>
     */
    @XStreamAlias("refund_fee")
    private Integer refundFee;

    /**
     * <pre>
     * 退款金额
     * settlement_refund_fee
     * 否
     * Int
     * 100
     * 去掉非充值代金券退款金额后的退款金额，退款金额=申请退款金额-非充值代金券退款金额，退款金额<=申请退款金额
     * <pre>
     */
    @XStreamAlias("settlement_refund_fee")
    private Integer settlementRefundFee;

    /**
     * <pre>
     * 订单金额
     * total_fee
     * 是
     * Int
     * 100
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * <pre>
     */
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * <pre>
     * 应结订单金额
     * settlement_total_fee
     * 否
     * Int
     * 100
     * 应结订单金额=订单金额-免充值代金券金额，应结订单金额<=订单金额。
     * <pre>
     */
    @XStreamAlias("settlement_total_fee")
    private Integer settlementTotalFee;

    /**
     * <pre>
     * 货币种类
     * fee_type
     * 否
     * String(8)
     * CNY
     * 订单金额货币类型，符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * <pre>
     */
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * <pre>
     * 现金支付金额
     * cash_fee
     * 是
     * Int
     * 100
     * 现金支付金额，单位为分，只能为整数，详见支付金额
     * <pre>
     */
    @XStreamAlias("cash_fee")
    private Integer cashFee;

    /**
     * <pre>
     * 现金退款金额
     * cash_refund_fee
     * 否
     * Int
     * 100
     * 现金退款金额，单位为分，只能为整数，详见支付金额
     * <pre>
     */
    @XStreamAlias("cash_refund_fee")
    private Integer cashRefundFee;

    /**
     * <pre>
     * 退款代金券使用数量
     * coupon_refund_count
     * 否
     * Int
     * 1
     * 退款代金券使用数量
     * <pre>
     */
    @XStreamAlias("coupon_refund_count")
    private Integer couponRefundCount;

    /**
     * <pre>
     * 代金券退款总金额.
     * coupon_refund_fee
     * 否
     * Int
     * 100
     * 代金券退款金额<=退款金额，退款金额-代金券或立减优惠退款金额为现金，说明详见代金券或立减优惠
     * </pre>
     */
    @XStreamAlias("coupon_refund_fee")
    private Integer couponRefundFee;

    private List<WxPayRefundCouponInfo> refundCoupons;

    @Override
    public void compose() {
        composeRefundCoupons();
    }
    /**
     * 组装生成退款代金券信息.
     */
    public void composeRefundCoupons() {
        List<WxPayRefundCouponInfo> coupons = Lists.newArrayList();
        Integer refundCount = this.getCouponRefundCount();
        if (refundCount == null) {
            //无退款代金券信息
            return;
        }

        for (int i = 0; i < refundCount; i++) {
            coupons.add(
                    new WxPayRefundCouponInfo(
                            this.getXmlValue("xml/coupon_refund_id_" + i),
                            this.getXmlValueAsInt("xml/coupon_refund_fee_" + i),
                            this.getXmlValue("xml/coupon_type_" + i)
                    )
            );
        }

        this.setRefundCoupons(coupons);
    }


    @Override
    protected void loadXML(Document d) {
        transactionId = readXMLString(d, "transaction_id");
        outTradeNo = readXMLString(d, "out_trade_no");
        outRefundNo = readXMLString(d, "out_refund_no");
        refundId = readXMLString(d, "refund_id");
        refundFee = readXMLInteger(d, "refund_fee");
        settlementRefundFee = readXMLInteger(d, "settlement_refund_fee");
        totalFee = readXMLInteger(d, "total_fee");
        settlementTotalFee = readXMLInteger(d, "settlement_total_fee");
        feeType = readXMLString(d, "fee_type");
        cashFee = readXMLInteger(d, "cash_fee");
        cashRefundFee = readXMLInteger(d, "cash_refund_fee");
        couponRefundCount = readXMLInteger(d, "coupon_refund_count");
        couponRefundFee = readXMLInteger(d, "coupon_refund_fee");
    }

    /**
     * <pre>
     *  退款代金券信息.
     * </pre>
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class WxPayRefundCouponInfo implements Serializable {
        private static final long serialVersionUID = 4280122381292401036L;

        /**
         * <pre>
         * 字段名：退款代金券ID.
         * 变量名：coupon_refund_id_$n
         * 是否必填：否
         * 类型：String(20)
         * 示例值：10000
         * 描述：退款代金券ID, $n为下标，从0开始编号
         * </pre>
         */
        @XStreamAlias("coupon_refund_id")
        private String couponRefundId;

        /**
         * <pre>
         * 字段名：单个代金券退款金额.
         * 变量名：coupon_refund_fee_$n
         * 是否必填：否
         * 类型：Int
         * 示例值：100
         * 描述：单个退款代金券支付金额, $n为下标，从0开始编号
         * </pre>
         */
        @XStreamAlias("coupon_refund_fee")
        private Integer couponRefundFee;

        /**
         * <pre>
         * 字段名：代金券类型.
         * 变量名：coupon_type_$n
         * 是否必填：否
         * 类型：String(8)
         * 示例值：CASH
         * 描述：CASH--充值代金券 , NO_CASH---非充值代金券。
         * 开通免充值券功能，并且订单使用了优惠券后有返回（取值：CASH、NO_CASH）。
         * 订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_0
         * </pre>
         */
        @XStreamAlias("coupon_type")
        private String couponType;

    }
}