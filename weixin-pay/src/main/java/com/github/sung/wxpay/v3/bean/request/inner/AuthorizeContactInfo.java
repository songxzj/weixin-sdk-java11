package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 联系人信息
 * 联系人是商户的超级管理员，将接收开户信息及日常重要管理信息，请确定超级管理员为商户法定代表人或负责人再进行操作。如超级管理员非商户法定代表人或负责人，请联系法定代表人或负责人提交申请。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorizeContactInfo extends BaseV3Inner {
    private static final long serialVersionUID = -1964123953977231935L;


    /**
     * 联系人姓名
     * name
     * string(1024)
     * 是
     */
    @Required
    @SerializedName("name")
    private String name;

    /**
     * 联系人手机号
     * mobile
     * string(1024)
     * 是
     */
    @Required
    @SerializedName("mobile")
    private String mobile;

    /**
     * 联系人身份证号码
     * id_card_number
     * string(1024)
     * 是
     */
    @Required
    @SerializedName("id_card_number")
    private String idCardNumber;


    @Override
    public void checkConstraints() throws WxErrorException {
    }

}
