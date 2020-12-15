package com.github.sung.wxpay.v3.bean.request.inner;

import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;

/**
 * 结算银行账户
 * 1、请填写商家提现收款的银行账户信息。
 * 2、若结算规则id为“719、721、716、717、730、739、727、738、726”，可选填结算账户。
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class BankAccountInfo extends BaseV3Inner {
    private static final long serialVersionUID = -1561772828453300867L;


    /**
     * 账户类型
     * bank_account_type
     * enum
     * 是
     */
    @Required
    @SerializedName("bank_account_type")
    private String bankAccountType;

    /**
     * 开户名称
     * account_name
     * string(2048)
     * 是
     */
    @Required
    @SerializedName("account_name")
    private String accountName;

    /**
     * 开户银行
     * account_bank
     * string(128)
     * 是
     */
    @Required
    @SerializedName("account_bank")
    private String accountBank;

    /**
     * 开户银行省市编码
     * bank_address_code
     * string(128)
     * 是
     */
    @Required
    @SerializedName("bank_address_code")
    private String bankAddressCode;


    /**
     * 开户银行联行号
     * bank_branch_id
     * string(128)
     */
    @SerializedName("bank_branch_id")
    private String bankBranchId;

    /**
     * 开户银行全称（含支行)
     * bank_name
     * string(128)
     */
    @SerializedName("bank_name")
    private String bankName;

    /**
     * 银行账号
     * account_number
     * string(2048)
     * 是
     */
    @Required
    @SerializedName("account_number")
    private String accountNumber;


    @Override
    public void checkConstraints() throws WxErrorException {

    }
}
