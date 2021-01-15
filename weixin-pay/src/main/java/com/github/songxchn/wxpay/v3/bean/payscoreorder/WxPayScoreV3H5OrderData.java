package com.github.songxchn.wxpay.v3.bean.payscoreorder;

import cn.hutool.core.util.RandomUtil;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.wxpay.constant.WxPayConstants;
import com.github.songxchn.wxpay.util.SignUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * H5调起支付分
 */
@Data
public class WxPayScoreV3H5OrderData implements Serializable {
    private static final long serialVersionUID = 8075882603896269596L;
    /**
     * 跳转类型
     * businessType
     * string[1,16]
     * 是
     */
    private String businessType;

    /**
     * 业务参数
     * queryString
     * string[1,2048]
     * 是
     */
    private String queryString;


    private WxPayScoreV3H5OrderData(String queryString) {
        this.businessType = "wxpayScoreDetail";
        this.queryString = queryString;
    }

    public static WxPayScoreV3H5OrderDataBuilder newBuilder() {
        return new WxPayScoreV3H5OrderDataBuilder();
    }

    public static class WxPayScoreV3H5OrderDataBuilder {

        /**
         * 商户号
         * mch_id
         * string[1,32]
         * 是
         */
        @XStreamAlias("mch_id")
        private String mchId;

        /**
         * 服务ID
         * service_id
         * string[1,32]
         * 是
         */
        @XStreamAlias("service_id")
        private String serviceId;

        /**
         * 商户服务订单号
         * out_order_no
         * string[1,32]
         * 是
         */
        @XStreamAlias("out_order_no")
        private String outOrderNo;

        /**
         * 时间戳
         * timestamp
         * string[1,32]
         * 是
         */
        @XStreamAlias("timestamp")
        private String timestamp;

        /**
         * 随机字符串
         * nonce_str
         * string[1,32]
         * 是
         */
        @XStreamAlias("nonce_str")
        private String nonceStr;

        /**
         * 签名方式
         * sign_type
         * string[1,32]
         * 是
         */
        @XStreamAlias("sign_type")
        private String signType;

        /**
         * 签名
         * sign
         * string[1,64]
         * 是
         */
        @XStreamAlias("sign")
        private String sign;

        public WxPayScoreV3H5OrderDataBuilder mchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public WxPayScoreV3H5OrderDataBuilder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public WxPayScoreV3H5OrderDataBuilder outOrderNo(String outOrderNo) {
            this.outOrderNo = outOrderNo;
            return this;
        }

        public WxPayScoreV3H5OrderData buildWithMchKey(String mchKey) throws WxErrorException, UnsupportedEncodingException {
            if (StringUtils.isBlank(this.mchId)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "mchId 必须提供值");
            }
            if (StringUtils.isBlank(this.serviceId)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "serviceId 必须提供值");
            }
            if (StringUtils.isBlank(this.outOrderNo)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "outOrderNo 必须提供值");
            }
            this.timestamp = String.valueOf(System.currentTimeMillis() / 1000);
            this.nonceStr = RandomUtil.randomString(32);
            this.signType = WxPayConstants.SignType.HMAC_SHA256;
            this.sign = SignUtils.createSign(this, this.signType, mchKey, new String[0]);

            StringBuffer queryString = new StringBuffer("mch_id=").append(urlEncode(this.mchId));
            queryString.append("&service_id=").append(urlEncode(this.serviceId))
                    .append("&out_order_no=").append(urlEncode(this.outOrderNo))
                    .append("&timestamp=").append(urlEncode(this.timestamp))
                    .append("&nonce_str=").append(urlEncode(this.nonceStr))
                    .append("&sign_type=").append(urlEncode(this.signType))
                    .append("&sign=").append(urlEncode(this.sign));


            WxPayScoreV3H5OrderData data = new WxPayScoreV3H5OrderData(queryString.toString());

            return data;
        }

        private String urlEncode(String str) {
            try {
                return URLEncoder.encode(str, WxPayConstants.DEFAULT_CHARSET);
            } catch (UnsupportedEncodingException e) {
                return "";
            }
        }
    }
}
