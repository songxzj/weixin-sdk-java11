package com.github.songxchn.wxpay.v3.bean.request.marketing.busifavor;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.songxchn.wxpay.v3.bean.result.marketing.busifavor.WxBusifavorStockStateResult;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

/**
 * version:2020.07.24
 * 查询商家券详情API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/marketing/busifavor/chapter3_2.shtml">
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxBusifavorStockStateRequest extends BaseWxPayV3Request<WxBusifavorStockStateResult> {
    private static final long serialVersionUID = -1197810332785871062L;


    /**
     * 批次号
     * stock_id
     * string[1,20]
     * 是
     */
    @Required
    @SerializedName("stock_id")
    private String stockId;

    @Override
    public String routing() {
        return "/v3/marketing/busifavor/stocks/" + this.stockId;
    }

    @Override
    public Class<WxBusifavorStockStateResult> getResultClass() {
        return WxBusifavorStockStateResult.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.GET;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {

    }
}
