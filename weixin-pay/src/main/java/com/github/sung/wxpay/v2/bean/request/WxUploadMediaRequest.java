package com.github.sung.wxpay.v2.bean.request;


import com.github.sung.wxcommon.annotation.Required;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.github.sung.wxpay.constant.WxMediaConstants;
import com.github.sung.wxpay.v2.bean.result.WxUploadMediaResult;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.File;
import java.util.Map;

/**
 * 图片上传API (签名方式用MD5返回验签才不会报错)
 * <a href="https://pay.weixin.qq.com/wiki/doc/api/xiaowei.php?chapter=19_9">
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
@Accessors(chain = true)
public class WxUploadMediaRequest extends BaseWxPayRequest<WxUploadMediaResult> {
    private static final long serialVersionUID = -3793459253812619225L;


    /**
     * 图片文件
     */
    @Required
    private File file;

    /**
     * <pre>
     * 媒体文件内容hash值
     * media_hash
     * 是
     * String(32)
     *
     * 根据媒体文件内容进行MD5计算后的值，注意小写
     * </pre>
     */
    @Required
    @XStreamAlias("media_hash")
    private String mediaHash;


    @Override
    public String routing() {
        return "/secapi/mch/uploadmedia";
    }

    @Override
    public Class<WxUploadMediaResult> getResultClass() {
        return WxUploadMediaResult.class;
    }

    @Override
    public boolean isUseKey() {
        return true;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {
        String fileName = this.file.getName();
        String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (!WxMediaConstants.IMAGE_SUFFIX_LIST.contains(fileType)) {
            throw new WxErrorException(WxErrorExceptionFactor.FILE_ERROR);
        }
    }

    @Override
    protected void storeMap(Map<String, String> map) {
        map.put("media_hash", this.mediaHash);
    }

    @Override
    public boolean isIgnoreAppid() {
        return true;
    }

    @Override
    public boolean isIgnoreSubAppId() {
        return true;
    }

    @Override
    public boolean isIgnoreSubMchId() {
        return true;
    }



}
