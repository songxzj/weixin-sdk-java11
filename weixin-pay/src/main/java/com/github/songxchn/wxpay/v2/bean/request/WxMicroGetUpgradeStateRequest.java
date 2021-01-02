package com.github.songxchn.wxpay.v2.bean.request;

import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.wxpay.v2.bean.result.WxMicroGetUpgradeStateResult;
import com.github.songxchn.common.exception.WxErrorException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * 小微商户查询升级申请单状态
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/xiaowei.php?chapter=28_3&index=3">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxMicroGetUpgradeStateRequest extends BaseWxPayRequest<WxMicroGetUpgradeStateResult> {
    private static final long serialVersionUID = -8597240185724291301L;
    /**
     * <pre>
     * 接口版本号
     * version
     * 是
     * String(32)
     * 1.0
     * 默认值1.0
     * </pre>
     */
    @Builder.Default
    @Required
    @XStreamAlias("version")
    private String version = "1.0";


    @Override
    public String routing() {
        return "/applyment/micro/getupgradestate";
    }

    @Override
    public Class<WxMicroGetUpgradeStateResult> getResultClass() {
        return WxMicroGetUpgradeStateResult.class;
    }

    @Override
    public boolean isUseKey() {
        return true;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
    }

    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("version", this.version);
    }

    @Override
    public boolean isIgnoreAppid() {
        return true;
    }

    @Override
    public boolean isIgnoreSubAppId() {
        return true;
    }

}
