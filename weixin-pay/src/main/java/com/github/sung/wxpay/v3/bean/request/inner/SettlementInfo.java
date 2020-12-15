package com.github.sung.wxpay.v3.bean.request.inner;


import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * 结算规则
 * 请填写商家的结算费率规则、特殊资质等信息。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class SettlementInfo extends BaseV3Inner {
    private static final long serialVersionUID = -2042715669948059183L;
    public static final String DEFAULT_ACTIVITIES_ID = "20191030111cff5b5e";


    /**
     * 入驻结算规则ID
     * settlement_id
     * string
     * 是
     */
    @Required
    @SerializedName("settlement_id")
    private String settlementId;

    /**
     * 所属行业
     * qualification_type
     * string
     * 是
     */
    @Required
    @SerializedName("qualification_type")
    private String qualificationType;

    /**
     * 特殊资质图片
     * qualifications
     * string(1280)
     */
    @SerializedName("qualifications")
    private List<String> qualifications;

    /**
     * 优惠费率活动ID
     * activities_id
     * string
     * 否
     */
    @SerializedName("activities_id")
    private String activitiesId;

    /**
     * 优惠费率活动值
     * activities_rate
     * string(50)
     * 否
     */
    @SerializedName("activities_rate")
    private String activitiesRate;

    /**
     * 优惠费率活动补充材料
     * activities_additions
     * string(1024)
     * 否
     */
    @SerializedName("activities_additions")
    private List<String> activitiesAdditions;


    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
