package com.github.songxchn.wxpay.v3.bean.payscoreorder;

import cn.hutool.core.util.RandomUtil;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.wxpay.constant.WxPayConstants;
import com.github.songxchn.wxpay.util.SignUtils;
import com.google.common.base.Joiner;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * APP调起支付分
 */
@Data
public class WxPayScoreV3AppOrderData implements Serializable {
    private static final long serialVersionUID = 385454042787764114L;

    /**
     * 跳转类型
     * businessType
     * string[1,16]
     * 是
     */
    private String businessType;

    /**
     * 业务参数
     * query
     * string[1,2048]
     * 是
     */
    private String query;

    /**
     * 其他配置
     * extInfo
     * string[1,128]
     * 否
     */
    private String extInfo;

    private WxPayScoreV3AppOrderData(String query, String extInfo) {
        this.businessType = "wxpayScoreDetail";
        this.query = query;
        this.extInfo = extInfo;
    }

    public static WxPayScoreV3AppOrderDataBuilder newBuilder() {
        return new WxPayScoreV3AppOrderDataBuilder();
    }

    public static class WxPayScoreV3AppOrderDataBuilder {

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

        private String extInfo;

        public WxPayScoreV3AppOrderDataBuilder mchId(String mchId) {
            this.mchId = mchId;
            return this;
        }

        public WxPayScoreV3AppOrderDataBuilder serviceId(String serviceId) {
            this.serviceId = serviceId;
            return this;
        }

        public WxPayScoreV3AppOrderDataBuilder outOrderNo(String outOrderNo) {
            this.outOrderNo = outOrderNo;
            return this;
        }

        public WxPayScoreV3AppOrderDataBuilder extInfo(String extInfo) {
            this.extInfo = extInfo;
            return this;
        }

        public WxPayScoreV3AppOrderData buildWithMchKey(String mchKey) throws WxErrorException {
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
            this.sign = SignUtils.createSign(this, this.signType, mchKey, new String[]{"extInfo"});

            StringBuffer query = new StringBuffer("mch_id=").append(this.mchId);
            query.append("&service_id=").append(this.serviceId)
                    .append("&out_order_no=").append(this.outOrderNo)
                    .append("&timestamp=").append(this.timestamp)
                    .append("&nonce_str=").append(this.nonceStr)
                    .append("&sign_type=").append(this.signType)
                    .append("&sign=").append(this.sign);


            WxPayScoreV3AppOrderData data = new WxPayScoreV3AppOrderData(query.toString(), this.extInfo);

            return data;
        }
    }
}
