package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;


/**
 * 经营资料
 * query请填写商家的经营业务信息、售卖商品/提供服务场景信息。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BusinessInfo extends BaseV3Inner {
    private static final long serialVersionUID = -6823350069817058692L;


    /**
     * 商户简称
     * merchant_shortname
     * string(64)
     * 是
     */
    @Required
    @SerializedName("merchant_shortname")
    private String merchantShortname;

    /**
     * 客服电话
     * service_phone
     * string(32)
     * 是
     */
    @Required
    @SerializedName("service_phone")
    private String servicePhone;

    /**
     * 经营场景
     * sales_info
     * object
     * 是
     */
    @Required
    @SerializedName("sales_info")
    private SalesInfo salesInfo;

    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
