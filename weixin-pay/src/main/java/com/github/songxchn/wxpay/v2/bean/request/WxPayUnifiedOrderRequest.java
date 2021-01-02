package com.github.songxchn.wxpay.v2.bean.request;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.wxpay.v2.bean.result.WxPayUnifiedOrderResult;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.wxpay.constant.WxPayConstants;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

/**
 * 统一下单
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/jsapi_sl.php?chapter=9_1">
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxPayUnifiedOrderRequest extends BaseWxPayRequest<WxPayUnifiedOrderResult> {
    private static final long serialVersionUID = -2191207600359341238L;
    /**
     * <pre>
     * 字段名：接口版本号.
     * 变量名：version
     * 是否必填：单品优惠必填
     * 类型：String(32)
     * 示例值：1.0
     * 描述：单品优惠新增字段，接口版本号，区分原接口，默认填写1.0。
     * 入参新增version后，则支付通知接口也将返回单品优惠信息字段promotion_detail，请确保支付通知的签名验证能通过。
     * 更多信息，详见文档：https://pay.weixin.qq.com/wiki/doc/api/danpin.php?chapter=9_102&index=2
     * </pre>
     */
  /*@XStreamAlias("version")
  private String version;*/

    /**
     * <pre>
     * 设备号
     * device_info
     * 否
     * String(32)
     * 013467007045764
     * 终端设备号(门店号或收银设备ID)，注意：PC网页或JSAPI支付请传"WEB"
     * </pre>
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * <pre>
     * 电子发票入口开放标识
     * receipt
     * 否
     * String(8)
     * Y
     * Y，传入Y时，支付成功消息和支付详情页将出现开票入口。需要在微信支付商户平台或微信公众平台开通电子发票功能，传此字段才可生效
     * </pre>
     **/
    @XStreamAlias("receipt")
    private String receipt;

    /**
     * <pre>
     * 商品描述
     * body
     * 是
     * String(128)
     * 腾讯充值中心-QQ会员充值
     * 商品描述交易字段格式根据不同的应用场景建议按照以下格式上传：
     * （1）PC网站——传入浏览器打开的网站主页title名-实际商品名称，例如：腾讯充值中心-QQ会员充值；
     * （2） 公众号——传入公众号名称-实际商品名称，例如：腾讯形象店- image-QQ公仔；
     * （3） H5——应用在浏览器网页上的场景，传入浏览器打开的移动网页的主页title名-实际商品名称，例如：腾讯充值中心-QQ会员充值；
     * （4） 线下门店——门店品牌名-城市分店名-实际商品名称，例如： image形象店-深圳腾大- QQ公仔）
     * （5） APP——需传入应用市场上的APP名字-实际商品名称，天天爱消除-游戏充值。
     * </pre>
     **/
    @Required
    @XStreamAlias("body")
    private String body;


    /**
     * <pre>
     * 商品详情
     * detail
     * 否
     * String(6000)
     *
     * 商品详细描述，对于使用单品优惠的商户，改字段必须按照规范上传，详见“单品优惠参数说明”
     **/
    @XStreamAlias("detail")
    private String detail;

    /**
     * <pre>
     * 附加数据
     * attach
     * 否
     * String(127)
     * 说明
     * 附加数据，在查询API和支付通知中原样返回，该字段主要用于商户携带订单的自定义数据
     * </pre>
     **/
    @XStreamAlias("attach")
    private String attach;

    /**
     * <pre>
     * 商户订单号
     * out_trade_no
     * 是
     * String(32)
     * 1217752501201407033233368018
     * 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*且在同一个商户号下唯一。详见商户订单号
     * </pre>
     **/
    @Required
    @XStreamAlias("out_trade_no")
    private String outTradeNo;

    /**
     * <pre>
     * 货币类型
     * fee_type
     * 否
     * String(16)
     * CNY
     * 符合ISO 4217标准的三位字母代码，默认人民币：CNY，其他值列表详见货币类型
     * </pre>
     **/
    @XStreamAlias("fee_type")
    private String feeType;

    /**
     * <pre>
     * 总金额
     * total_fee
     * 是
     * Int
     * 888
     * 订单总金额，单位为分，只能为整数，详见支付金额
     * </pre>
     **/
    @Required
    @XStreamAlias("total_fee")
    private Integer totalFee;

    /**
     * <pre>
     * 终端IP
     * spbill_create_ip
     * 是
     * String(64)
     * 123.12.12.123
     * 支持IPV4和IPV6两种格式的IP地址。调用微信支付API的机器IP
     * </pre>
     **/
    @Required
    @XStreamAlias("spbill_create_ip")
    private String spbillCreateIp;

    /**
     * <pre>
     * 交易起始时间
     * time_start
     * 否
     * String(14)
     * 20091225091010
     * 订单生成时间，格式为yyyyMMddHHmmss，如2009年12月25日9点10分10秒表示为20091225091010。其他详见时间规则
     * </pre>
     */
    @XStreamAlias("time_start")
    private String timeStart;

    /**
     * <pre>
     * 交易结束时间
     * time_expire
     * 否
     * String(14)
     * 20091227091010
     * 订单失效时间，格式为yyyyMMddHHmmss，如2009年12月27日9点10分10秒表示为20091227091010。订单失效时间是针对订单号而言的，由于在请求支付的时候有一个必传参数prepay_id只有两小时的有效期，所以在重入时间超过2小时的时候需要重新请求下单接口获取新的prepay_id。其他详见时间规则
     * time_expire只能第一次下单传值，不允许二次修改，二次修改系统将报错。如用户支付失败后，需再次支付，需更换原订单号重新下单。
     * 建议：最短失效时间间隔大于1分钟
     * </pre>
     */
    @XStreamAlias("time_expire")
    private String timeExpire;

    /**
     * <pre>
     * 订单优惠标记
     * goods_tag
     * 否
     * String(32)
     * WXG
     * 订单优惠标记，代金券或立减优惠功能的参数，说明详见代金券或立减优惠
     * </pre>
     **/
    @XStreamAlias("goods_tag")
    private String goodsTag;

    /**
     * <pre>
     * 通知地址
     * notify_url
     * 是
     * String(256)
     * http://www.weixin.qq.com/wxpay/pay.php
     * 接收微信支付异步通知回调地址，通知url必须为直接可访问的url，不能携带参数。
     * </pre>
     */
    @Required
    @XStreamAlias("notify_url")
    private String notifyUrl;

    /**
     * <pre>
     * 交易类型
     * trade_type
     * 是
     * String(16)
     * JSAPI
     * JSAPI -JSAPI支付
     * NATIVE -Native支付
     * APP -APP支付
     * 说明详见参数规定
     * </pre>
     */
    @Required
    @XStreamAlias("trade_type")
    private String tradeType;

    /**
     * <pre>
     * 商品ID
     * product_id
     * 否
     * String(32)
     * 12235413214070356458058
     * trade_type=NATIVE时，此参数必传。此id为二维码中包含的商品ID，商户自行定义。
     * </pre>
     */
    @XStreamAlias("product_id")
    private String productId;

    /**
     * <pre>
     * 指定支付方式
     * limit_pay
     * 否
     * String(32)
     * no_credit
     * no_credit--指定不能使用信用卡支付
     * </pre>
     **/
    @XStreamAlias("limit_pay")
    private String limitPay;

    /**
     * <pre>
     * 用户标识
     * openid
     * 否
     * String(128)
     * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * trade_type=JSAPI，此参数必传，用户在主商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
     * </pre>
     */
    @XStreamAlias("openid")
    private String openid;

    /**
     * <pre>
     * 用户子标识
     * sub_openid
     * 否
     * String(128)
     * oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
     * trade_type=JSAPI，此参数必传，用户在子商户appid下的唯一标识。openid和sub_openid可以选传其中之一，如果选择传sub_openid,则必须传sub_appid。下单前需要调用【网页授权获取用户信息】接口获取到用户的Openid。
     * </pre>
     */
    @XStreamAlias("sub_openid")
    private String subOpenid;


    /**
     * <pre>
     * 场景信息
     * scene_info
     * 否
     * String(256)
     * {"store_info" : {
     * "id": "SZTX001",
     * "name": "腾大餐厅",
     * "area_code": "440305",
     * "address": "科技园中一路腾讯大厦" }}
     * 该字段常用于线下活动时的场景信息上报，支持上报实际门店信息，商户也可以按需求自己上报相关信息。该字段为JSON对象数据，对象格式为{"store_info":{"id": "门店ID","name": "名称","area_code": "编码","address": "地址" }} ，字段详细说明请点击行前的+展开
     * </pre>
     */
    @XStreamAlias("scene_info")
    private String sceneInfo;
    /**
     * <pre>
     * 字段名：浏览器指纹.
     * 变量名：fingerprint
     * 是否必填：否
     * 详细参考 https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=15_7&index=6
     * </pre>
     */
    /*@XStreamAlias("fingerprint")
    private String fingerprint;*/
    /**
     * <pre>
     * 字段名：是否指定服务商分账.
     * 变量名：profit_sharing
     * 是否必填：否
     * 详情：Y-是，需要分账  N-否，不分账，字母要求大写，不传默认不分账
     * 详细参考 https://pay.weixin.qq.com/wiki/doc/api/allocation_sl.php?chapter=24_3&index=3
     * </pre>
     */
    @XStreamAlias("profit_sharing")
    private String profitSharing;

    @XStreamAlias("user_creid")
    private String userCreid;

    @XStreamAlias("user_truename")
    private String userTruename;


    @Override
    public String routing() {
        return "/pay/unifiedorder";
    }

    @Override
    public Class<WxPayUnifiedOrderResult> getResultClass() {
        return WxPayUnifiedOrderResult.class;
    }

    @Override
    public boolean isUseKey() {
        return false;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        if (WxPayConstants.TradeType.NATIVE.equals(this.tradeType) && StringUtils.isBlank(this.productId)) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当trade_type是'NATIVE'时，需指定非空的product_id值");
        }
    }


    @Override
    protected void storeMap(Map<String, String> map) {
        //map.put("version", version);
        map.put("device_info", deviceInfo);
        map.put("receipt", receipt);
        map.put("body", body);
        map.put("detail", detail);
        map.put("attach", attach);
        map.put("out_trade_no", outTradeNo);
        map.put("fee_type", feeType);
        map.put("total_fee", totalFee.toString());
        map.put("spbill_create_ip", spbillCreateIp);
        map.put("time_start", timeStart);
        map.put("time_expire", timeExpire);
        map.put("goods_tag", goodsTag);
        map.put("notify_url", notifyUrl);
        map.put("trade_type", tradeType);
        map.put("product_id", productId);
        map.put("limit_pay", limitPay);
        map.put("openid", openid);
        map.put("sub_openid", subOpenid);
        map.put("scene_info", sceneInfo);
        //map.put("fingerprint", fingerprint);
        map.put("profit_sharing", profitSharing);
        map.put("user_creid", this.userCreid);
        map.put("user_truename", this.userTruename);
    }

}
