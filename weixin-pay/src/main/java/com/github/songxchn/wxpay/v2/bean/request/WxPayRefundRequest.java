package com.github.songxchn.wxpay.v2.bean.request;


import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.wxpay.v2.bean.result.WxPayRefundResult;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.wxpay.constant.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.Map;

/**
 * 申请退款
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_4">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPayRefundRequest extends BaseWxPayRequest<WxPayRefundResult> {
    private static final long serialVersionUID = -3368136183226852178L;


    private static final String[] REFUND_ACCOUNT = new String[]{
            WxPayConstants.RefundAccountSource.RECHARGE_FUNDS, WxPayConstants.RefundAccountSource.UNSETTLED_FUNDS};


    /**
     * <pre>
     * 微信订单号
     * transaction_id
     * 跟out_trade_no二选一
     * String(32)
     * 1217752501201407033233368018
     * 微信生成的订单号，在支付通知中有返回
     * </pre>
     */
    @XStreamAlias("transaction_id")
    private String transactionId;
    /**
     * <pre>
     * 商户订单号
     * out_trade_no
     * 跟transaction_id二选一
     * String(32)
     * 1217752501201400000000000000
     * 描述：商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     * transaction_id、out_trade_no二选一，如果同时存在优先级：transaction_id> out_trade_no
     * </pre>
     */
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
     */
    @Required
    @XStreamAlias("out_refund_no")
    private String outRefundNo;
    /**
     * <pre>
     * 订单金额
     * total_fee
     * 是
     * Int
     * 100
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * </pre>
     */
    @Required
    @XStreamAlias("total_fee")
    private Integer totalFee;
    /**
     * <pre>
     * 申请退款金额
     * refund_fee
     * 是
     * Int
     * 100
     * 退款总金额，单位为分，只能为整数，可部分退款。详见支付金额
     * </pre>
     */
    @Required
    @XStreamAlias("refund_fee")
    private Integer refundFee;
    /**
     * <pre>
     * 退款货币种类
     * refund_fee_type
     * 否
     * String(8)
     * CNY
     * 退款货币类型，需与支付一致，或者不填。符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * </pre>
     */
    @XStreamAlias("refund_fee_type")
    private String refundFeeType;

    /**
     * <pre>
     * 退款资金来源
     * refund_account
     * 否
     * String(30)
     * REFUND_SOURCE_RECHARGE_FUNDS
     * 仅针对老资金流商户使用
     * REFUND_SOURCE_UNSETTLED_FUNDS---未结算资金退款（默认使用未结算资金退款）
     * REFUND_SOURCE_RECHARGE_FUNDS---可用余额退款
     * </pre>
     */
    @XStreamAlias("refund_account")
    private String refundAccount;

    /**
     * <pre>
     * 退款原因
     * refund_desc
     * 否
     * String(80)
     * 商品已售完
     * 若商户传入，会在下发给用户的退款消息中体现退款原因
     * 注意：若订单退款金额≤1元，且属于部分退款，则不会在退款消息中体现退款原因
     * </pre>
     */
    @XStreamAlias("refund_desc")
    private String refundDesc;

    /**
     * <pre>
     * 退款结果通知url
     * notify_url
     * 否
     * String(256)
     * https://weixin.qq.com/notify/
     * 异步接收微信支付退款结果通知的回调地址，通知URL必须为外网可访问的url，不允许带参数
     * 如果参数中传了notify_url，则商户平台上配置的回调地址将不会生效。
     * </pre>
     */
    @XStreamAlias("notify_url")
    private String notifyUrl;

    @Override
    public String routing() {
        return "/secapi/pay/refund";
    }

    @Override
    public Class<WxPayRefundResult> getResultClass() {
        return WxPayRefundResult.class;
    }

    @Override
    public boolean isUseKey() {
        return true;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        if (StringUtils.isNotBlank(this.refundAccount)) {
            if (!ArrayUtils.contains(REFUND_ACCOUNT, this.refundAccount)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE,
                        String.format("refund_account目前必须为%s其中之一,实际值：%s", Arrays.toString(REFUND_ACCOUNT), this.getRefundAccount()));
            }
        }

        if (StringUtils.isBlank(this.outTradeNo) && StringUtils.isBlank(this.transactionId)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "transaction_id 和 out_trade_no 不能同时为空");
        }

    }

    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("transaction_id", this.transactionId);
        map.put("out_trade_no", this.outTradeNo);
        map.put("out_refund_no", this.outRefundNo);
        map.put("total_fee", this.totalFee.toString());
        map.put("refund_fee", this.refundFee.toString());
        map.put("refund_fee_type", this.refundFeeType);
        map.put("refund_account", this.refundAccount);
        map.put("refund_desc", this.refundDesc);
        map.put("notify_url", this.notifyUrl);
    }
}
