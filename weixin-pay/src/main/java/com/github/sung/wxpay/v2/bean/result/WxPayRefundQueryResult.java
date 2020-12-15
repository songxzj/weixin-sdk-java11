package com.github.sung.wxpay.v2.bean.result;


import com.github.sung.wxpay.v2.bean.result.inner.RefundRecord;
import com.github.sung.wxpay.v2.bean.result.inner.WxPayRefundCouponInfo;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import org.w3c.dom.Document;

import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayRefundQueryResult extends BaseWxPayResult {
    private static final long serialVersionUID = 1002049686675489309L;


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
     * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     * </pre>
     **/
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

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
     * 订单总退款次数
     * total_refund_count
     * 否
     * Int
     * 35
     * 订单总共已发生的部分退款次数，当请求参数传入offset后有返回
     * </pre>
     */
    @XStreamAlias("refund_count")
    private Integer refundCount;


    /**
     * <pre>
     * 退款笔数
     * refund_count
     * 是
     * Int
     * 1
     * 当前返回退款笔数
     * </pre>
     */
    @XStreamAlias("total_refund_count")
    private Integer totalRefundCount;


    private List<RefundRecord> refundRecords;

    @Override
    public void compose() {
        composeRefundRecords();
    }

    /**
     * 组装生成退款记录属性的内容.
     */
    public void composeRefundRecords() {
        if (this.refundCount != null && this.refundCount > 0) {
            this.refundRecords = Lists.newArrayList();

            for (int i = 0; i < this.refundCount; i++) {
                RefundRecord refundRecord = new RefundRecord();
                this.refundRecords.add(refundRecord);

                refundRecord.setOutRefundNo(this.getXmlValue("xml/out_refund_no_" + i));
                refundRecord.setRefundId(this.getXmlValue("xml/refund_id_" + i));
                refundRecord.setRefundChannel(this.getXmlValue("xml/refund_channel_" + i));
                refundRecord.setRefundFee(this.getXmlValueAsInt("xml/refund_fee_" + i));
                refundRecord.setSettlementRefundFee(this.getXmlValueAsInt("xml/settlement_refund_fee_" + i));
                refundRecord.setCouponRefundFee(this.getXmlValueAsInt("xml/coupon_refund_fee_" + i));
                refundRecord.setCouponRefundCount(this.getXmlValueAsInt("xml/coupon_refund_count_" + i));
                refundRecord.setRefundStatus(this.getXmlValue("xml/refund_status_" + i));
                refundRecord.setRefundRecvAccount(this.getXmlValue("xml/refund_recv_accout_" + i));
                refundRecord.setRefundAccount(this.getXmlValue("xml/refund_account_" + i));
                refundRecord.setRefundSuccessTime(this.getXmlValue("xml/refund_success_time_" + i));

                if (refundRecord.getCouponRefundCount() == null || refundRecord.getCouponRefundCount() == 0) {
                    continue;
                }

                List<WxPayRefundCouponInfo> coupons = Lists.newArrayList();
                for (int j = 0; j < refundRecord.getCouponRefundCount(); j++) {
                    coupons.add(
                            new WxPayRefundCouponInfo(
                                    this.getXmlValue("xml/coupon_refund_id_" + i + "_" + j),
                                    this.getXmlValueAsInt("xml/coupon_refund_fee_" + i + "_" + j),
                                    this.getXmlValue("xml/coupon_type_" + i + "_" + j)
                            )
                    );
                }

                refundRecord.setRefundCoupons(coupons);
            }

        }
    }

    /**
     * 从XML结构中加载额外的熟悉
     *
     * @param d Document
     */
    @Override
    protected void loadXML(Document d) {
        transactionId = readXMLString(d, "transaction_id");
        outTradeNo = readXMLString(d, "out_trade_no");
        totalFee = readXMLInteger(d, "total_fee");
        settlementTotalFee = readXMLInteger(d, "settlement_total_fee");
        feeType = readXMLString(d, "fee_type");
        cashFee = readXMLInteger(d, "cash_fee");
        refundCount = readXMLInteger(d, "refund_count");
        totalRefundCount = readXMLInteger(d, "total_refund_count");
    }

}

