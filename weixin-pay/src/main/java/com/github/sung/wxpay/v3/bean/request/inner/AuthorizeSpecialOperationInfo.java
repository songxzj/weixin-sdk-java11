package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

import java.util.List;


/**
 * +经营许可证信息
 * 特殊行业的经营许可证信息
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeSpecialOperationInfo extends BaseV3Inner {
    private static final long serialVersionUID = 2352980450354506314L;


    /**
     * 行业id
     * category_id
     * uint32
     * 是
     */
    @Required
    @SerializedName("category_id")
    private String categoryId;

    /**
     * 店内环境照片
     * store_indoor_copy
     * array
     * 否
     */
    @SerializedName("store_indoor_copy")
    private List<String> storeIndoorCopy;




    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
