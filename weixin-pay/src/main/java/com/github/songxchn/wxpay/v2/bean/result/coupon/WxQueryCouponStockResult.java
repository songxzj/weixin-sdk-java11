package com.github.songxchn.wxpay.v2.bean.result.coupon;

import com.github.songxchn.wxpay.v2.bean.result.BaseWxPayResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.w3c.dom.Document;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxQueryCouponStockResult extends BaseWxPayResult {
    private static final long serialVersionUID = -1363759004010269564L;

    /**
     * 设备号
     * device_info
     * 否
     * 123456sb
     * String(32)
     * 微信支付分配的终端设备号，
     */
    @XStreamAlias("device_info")
    private String deviceInfo;

    /**
     * 代金券批次ID
     * coupon_stock_id
     * 是
     * 1757
     * String
     * 代金券批次Id
     */
    @XStreamAlias("coupon_stock_id")
    private String couponStockId;

    /**
     * 代金券名称
     * coupon_name
     * 否
     * 测试代金券
     * String
     * 代金券名称
     */
    @XStreamAlias("coupon_name")
    private String couponName;

    /**
     * 代金券面额
     * coupon_value
     * 是
     * 5
     * Unsinged
     * int
     * 代金券面值,单位是分
     */
    @XStreamAlias("coupon_value")
    private Integer couponValue;

    /**
     * 代金券使用最低限额
     * coupon_mininumn
     * 否
     * 10
     * Unsinged
     * int
     * 代金券使用最低限额,单位是分
     */
    @XStreamAlias("coupon_mininumn")
    private Integer couponMinimum;

    /**
     * 代金券批次状态
     * coupon_stock_status
     * 是
     * 4
     * int
     * 批次状态： 1-未激活；2-审批中；4-已激活；8-已作废；16-中止发放；
     */
    @XStreamAlias("coupon_stock_status")
    private Integer couponStockStatus;

    /**
     * 代金券数量
     * coupon_total
     * 是
     * 100
     * Unsigned
     * int
     * 代金券数量
     */
    @XStreamAlias("coupon_total")
    private Integer couponTotal;

    /**
     * 代金券最大领取数量
     * max_quota
     * 否
     * 1
     * Unsigned
     * int
     * 代金券每个人最多能领取的数量, 如果为0，则表示没有限制
     */
    @XStreamAlias("max_quota")
    private Integer maxQuota;

    /**
     * 代金券已经发送的数量
     * is_send_num
     * 否
     * 0
     * Unsigned
     * int
     * 代金券已经发送的数量
     */
    @XStreamAlias("is_send_num")
    private Integer isSendNum;

    /**
     * 生效开始时间
     * begin_time
     * 是
     * 1943787483
     * String
     * 格式为时间戳
     */
    @XStreamAlias("begin_time")
    private String beginTime;

    /**
     * 生效结束时间
     * end_time
     * 是
     * 1943787490
     * String
     * 格式为时间戳
     */
    @XStreamAlias("end_time")
    private String endTime;

    /**
     * 创建时间
     * create_time
     * 是
     * 1943787420
     * String
     * 格式为时间戳
     */
    @XStreamAlias("create_time")
    private String createTime;

    /**
     * 代金券预算额度
     * coupon_budget
     * 否
     * 500
     * Unsigned
     * int
     * 代金券预算额度
     */
    @XStreamAlias("coupon_budget")
    private Integer couponBudget;

    /**
     * 生效开始时间
     * begin_time_t
     * 是
     * 20181126152401
     * String
     * 格式为年月日时间制
     */
    @XStreamAlias("begin_time_t")
    private String beginTimeT;

    /**
     * 生效结束时间
     * end_time_t
     * 是
     * 20181126152401
     * String
     * 格式为年月日时间制
     */
    @XStreamAlias("end_time_t")
    private String endTimeT;

    /**
     * 创建时间
     * create_time_t
     * 是
     * 20181126152401
     * String
     * 格式为年月日时间制
     */
    @XStreamAlias("create_time_t")
    private String createTimeT;

    @Override
    protected void loadXml(Document d) {
        deviceInfo = readXmlString(d, "device_info");
        couponStockId = readXmlString(d, "coupon_stock_id");
        couponName = readXmlString(d, "coupon_name");
        couponValue = readXmlInteger(d, "coupon_value");
        couponMinimum = readXmlInteger(d, "coupon_mininumn");
        couponStockStatus = readXmlInteger(d, "coupon_stock_status");
        couponTotal = readXmlInteger(d, "coupon_total");
        maxQuota = readXmlInteger(d, "max_quota");
        isSendNum = readXmlInteger(d, "is_send_num");
        beginTime = readXmlString(d, "begin_time");
        endTime = readXmlString(d, "end_time");
        createTime = readXmlString(d, "create_time");
        couponBudget = readXmlInteger(d, "coupon_budget");
        beginTimeT = readXmlString(d, "begin_time_t");
        endTimeT = readXmlString(d, "end_time_t");
        createTimeT = readXmlString(d, "create_time_t");
    }
}
