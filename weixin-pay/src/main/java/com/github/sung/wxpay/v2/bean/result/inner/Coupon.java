package com.github.sung.wxpay.v2.bean.result.inner;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Coupon.
 */
@Data
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class Coupon implements Serializable {
    private static final long serialVersionUID = 2804713926351456949L;

    /**
     * <pre>
     * 代金券ID.
     * coupon_id_$n
     * 否
     * String(20)
     * 10000
     * 代金券ID, $n为下标，从0开始编号
     * </pre>
     */
    private String couponId;

    /**
     * <pre>
     * 代金券类型.
     * coupon_type_$n
     * 否
     * String
     * CASH
     * <li>CASH--充值代金券
     * <li>NO_CASH---非充值代金券
     * 	订单使用代金券时有返回（取值：CASH、NO_CASH）。$n为下标,从0开始编号，举例：coupon_type_$0
     * </pre>
     */
    private String couponType;


    /**
     * <pre>
     * 单个代金券支付金额.
     * coupon_fee_$n
     * 否
     * Int
     * 100
     * 单个代金券支付金额, $n为下标，从0开始编号
     * </pre>
     */
    private Integer couponFee;
}
