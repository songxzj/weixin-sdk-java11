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

/**
 * 小程序调起支付分(navigateToMiniProgram)
 */
@Data
public class WxPayScoreV3JsapiNavigateOrderData implements Serializable {
    private static final long serialVersionUID = 4711422765853290441L;

    /**
     * 公众号ID
     * appId
     * string[1,32]
     * 是
     */
    private String appId;

    /**
     * 路径
     * path
     * string[1,64]
     * 是
     */
    private String path;

    /**
     * 业务参数
     * extraData
     * Object
     * 是
     */
    private ExtraData extraData;

    @Data
    public static class ExtraData implements Serializable {
        private static final long serialVersionUID = 8088489952852987634L;

        private String mch_id;

        private String service_id;

        private String out_order_no;

        private String timestamp;

        private String nonce_str;

        private String sign_type;

        private String sign;
    }


    private WxPayScoreV3JsapiNavigateOrderData(String appId, ExtraData extraData) {
        this.appId = appId;
        this.path = "pages/record/detail";
        this.extraData = extraData;
    }

    public static WxPayScoreV3JsapiNavigateOrderDataBuilder newBuilder() {
        return new WxPayScoreV3JsapiNavigateOrderDataBuilder();
    }

    public static class WxPayScoreV3JsapiNavigateOrderDataBuilder {

        private String appId;

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

        public WxPayScoreV3JsapiNavigateOrderDataBuilder mchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public WxPayScoreV3JsapiNavigateOrderDataBuilder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public WxPayScoreV3JsapiNavigateOrderDataBuilder outOrderNo(String outOrderNo) {
            this.outOrderNo = outOrderNo;
            return this;
        }

        public WxPayScoreV3JsapiNavigateOrderData buildWithMchKey(String mchKey) throws WxErrorException {
            if (StringUtils.isBlank(this.appId)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "appId 必须提供值");
            }
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
            this.sign = SignUtils.createSign(this, this.signType, mchKey, new String[]{"appId"});

            ExtraData extraData = new ExtraData();
            extraData.mch_id = this.mchId;
            extraData.service_id = this.serviceId;
            extraData.out_order_no = this.outOrderNo;
            extraData.timestamp = this.timestamp;
            extraData.nonce_str = this.nonceStr;
            extraData.sign_type = this.signType;
            extraData.sign = this.sign;

            WxPayScoreV3JsapiNavigateOrderData data = new WxPayScoreV3JsapiNavigateOrderData(this.appId, extraData);

            return data;
        }
    }
}
