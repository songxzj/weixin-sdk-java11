package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 辅助证明材料信息
 * 主体类型为小微商户时，辅助证明材料信息必填。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeAssistProveInfo extends BaseV3Inner {
    private static final long serialVersionUID = 3012866869744162059L;

    /**
     * 小微经营类型
     * micro_biz_type
     * enum
     * 是
     */
    @Required
    @SerializedName("micro_biz_type")
    private String microBizType;

    /**
     * 门店名称
     * store_name
     * string(128)
     * 是
     */
    @Required
    @SerializedName("store_name")
    private String storeName;

    /**
     * 门店省市编码
     * store_address_code
     * string(16)
     * 是
     */
    @Required
    @SerializedName("store_address_code")
    private String storeAddressCode;

    /**
     * 门店地址
     * store_address
     * string(128)
     * 是
     */
    @Required
    @SerializedName("store_address")
    private String storeAddress;

    /**
     * 门店门头照片
     * store_header_copy
     * string(255)
     * 是
     */
    @Required
    @SerializedName("store_header_copy")
    private String storeHeaderCopy;

    /**
     * 店内环境照片
     * store_indoor_copy
     * string(255)
     * 是
     */
    @Required
    @SerializedName("store_indoor_copy")
    private String storeIndoorCopy;


    @Override
    public void checkConstraints() throws WxErrorException {
    }
}
