package com.github.sung.wxpay.v2.bean.result.inner;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
