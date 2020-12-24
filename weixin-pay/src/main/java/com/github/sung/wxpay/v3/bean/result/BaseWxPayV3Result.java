package com.github.sung.wxpay.v3.bean.result;

import com.github.sung.wxcommon.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;


@Data
public abstract class BaseWxPayV3Result implements Serializable {
    private static final long serialVersionUID = 7765784097135990901L;


    public void compose() {
    }

    public static <T extends BaseWxPayV3Result> T fromJson(String jsonString, Class<T> clz) {
        try {
            BaseWxPayV3Result t = WxGsonBuilder.create().fromJson(jsonString, clz);
            if (t == null) {
                return clz.newInstance();
            }
            t.compose();
            return (T) t;
        } catch (Exception e) {
            throw new RuntimeException("parse json error", e);
        }
    }
}
