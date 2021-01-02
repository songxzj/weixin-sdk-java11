package com.github.songxchn.wxpay.v2.bean.request;


import com.github.songxchn.wxpay.v2.bean.result.WxPayRefundQueryResult;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 查询退款
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/micropay_sl.php?chapter=9_5">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPayRefundQueryRequest extends BaseWxPayRequest<WxPayRefundQueryResult> {
    private static final long serialVersionUID = 9133714445598918248L;


    //************以下四选一************
    /**
     * 微信订单号
     * transaction_id
     * 四选一
     * String(28)
     * 1217752501201407033233368018
     * 微信订单号 查询的优先级是： refund_id > out_refund_no > transaction_id > out_trade_no
     */
    @XStreamAlias("transaction_id")
    private String transactionId;

    /**
     * 商户订单号
     * out_trade_no
     * String(32)
     * 1217752501201407033233368018
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。
     */
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * 商户退款单号
     * out_refund_no
     * String(64)
     * 1217752501201407033233368018
     * 商户系统内部的退款单号，商户系统内部唯一，只能是数字、大小写字母_-|*@ ，同一退款单号多次请求只退一笔。
     */
    @XStreamAlias("out_refund_no")
    private String outRefundNo;

    /**
     * 微信退款单号
     * refund_id
     * String(32)
     * 1217752501201407033233368018
     * 微信退款单号
     * refund_id、out_refund_no、out_trade_no、transaction_id四个参数必填一个，如果同时存在优先级为：
     * >out_refund_no>transaction_id>out_trade_no
     */
    @XStreamAlias("refund_id")
    private String refundId;


    /**
     * 偏移量
     * offset
     * 否
     * Int
     * 15
     * 偏移量，当部分退款次数超过10次时可使用，表示返回的查询结果从这个偏移量开始取记录
     */
    @XStreamAlias("offset")
    private String offset;


    @Override
    public String routing() {
        return "/pay/refundquery";
    }

    @Override
    public Class<WxPayRefundQueryResult> getResultClass() {
        return WxPayRefundQueryResult.class;
    }

    @Override
    public boolean isUseKey() {
        return false;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        if ((StringUtils.isBlank(this.transactionId) && StringUtils.isBlank(this.outTradeNo)
                && StringUtils.isBlank(this.outRefundNo) && StringUtils.isBlank(this.refundId)) ||
                (StringUtils.isNotBlank(this.transactionId) && StringUtils.isNotBlank(this.outTradeNo)
                        && StringUtils.isNotBlank(this.outRefundNo) && StringUtils.isNotBlank(this.refundId))) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "transaction_id，out_trade_no，out_refund_no，refund_id 必须四选一");
        }
    }

    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("transaction_id", transactionId);
        map.put("out_trade_no", outTradeNo);
        map.put("out_refund_no", outRefundNo);
        map.put("refund_id", refundId);
        map.put("offset", offset);
    }
}
