package com.github.songxchn.wxpay.v3.bean.result;

import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import com.github.songxchn.common.json.WxGsonBuilder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;


@Data
@Slf4j
public abstract class BaseWxPayV3Result implements Serializable {
    private static final long serialVersionUID = 7765784097135990901L;


    public void compose() {
    }

    /**
     * 默认不进行敏感加密
     */
    public boolean isSensitiveEncrypt() {
        return false;
    }

    public static <T extends BaseWxPayV3Result> T fromJson(String jsonString, Class<T> clz) throws WxErrorException {
        try {
            BaseWxPayV3Result t = WxGsonBuilder.create().fromJson(jsonString, clz);
            if (t == null) {
                t = clz.newInstance();
            }
            t.compose();
            return (T) t;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.PARSE_JSON_ERROR);
        }
    }
}
