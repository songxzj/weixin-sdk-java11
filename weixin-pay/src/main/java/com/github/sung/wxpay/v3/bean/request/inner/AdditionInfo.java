package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;

/**
 * 补充材料
 * 根据实际审核情况，额外要求商家提供指定的补充资料。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AdditionInfo extends BaseV3Inner {
    private static final long serialVersionUID = 218786077047813952L;

    /**
     * 法人开户承诺函
     * legal_person_commitment
     * string
     * 否
     */
    @SerializedName("legal_person_commitment")
    private String legalPersonCommitment;

    /**
     * 法人开户意愿视频
     * legal_person_video
     * string
     * 否
     */
    @SerializedName("legal_person_video")
    private String legalPersonVideo;

    /**
     * 补充材料
     * business_addition_pics
     * string(1280)
     * 否
     */
    @SerializedName("business_addition_pics")
    private List<String> businessAdditionPics;

    /**
     * 补充说明
     * business_addition_msg
     * string(512)
     * 否
     */
    @SerializedName("business_addition_msg")
    private String businessAdditionMsg;

    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
