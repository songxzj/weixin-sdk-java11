package com.github.songxchn.wxpay.v3.bean.request.media;

import com.github.songxchn.common.annotation.GsonExclude;
import com.github.songxchn.common.annotation.Required;
import com.github.songxchn.wxpay.constant.WxMediaConstants;
import com.github.songxchn.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.songxchn.wxpay.v3.bean.result.media.WxMediaUploadV3Result;
import com.github.songxchn.common.exception.WxErrorException;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.http.HttpMethod;

import java.io.File;

/**
 * 图片上传API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml">
 *
 * 视频上传API
 * <a href="https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_2.shtml"><
 */


@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxMediaUploadV3Request extends BaseWxPayV3Request<WxMediaUploadV3Result> {
    private static final long serialVersionUID = 3501150685142010901L;


    /**
     * 图片文件或者视频文件
     */
    @Required
    @GsonExclude
    private File file;

    /**
     * 文件名称
     * filename
     * string(128)
     * 否
     */
    @SerializedName("filename")
    private String filename;

    /**
     * 文件摘要
     * sha256
     * string(64)
     * 否
     */
    @SerializedName("sha256")
    private String sha256;


    @Override
    public String routing() {

        if (isImageFile()) {
            return "/v3/merchant/media/upload";
        } else if (isVideoFile()) {
            return "/v3/merchant/media/video_upload";
        } else {
            return "";
        }
    }

    @Override
    public Class<WxMediaUploadV3Result> getResultClass() {
        return WxMediaUploadV3Result.class;
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    protected void checkConstraints() throws WxErrorException {

    }

    public boolean isImageFile() {
        String fileType = this.filename.substring(this.filename.lastIndexOf(".") + 1);
        return WxMediaConstants.IMAGE_SUFFIX_LIST.contains(fileType);
    }

    public boolean isVideoFile() {
        String fileType = this.filename.substring(this.filename.lastIndexOf(".") + 1);
        return WxMediaConstants.VIDEO_SUFFIX_LIST.contains(fileType);
    }
}
