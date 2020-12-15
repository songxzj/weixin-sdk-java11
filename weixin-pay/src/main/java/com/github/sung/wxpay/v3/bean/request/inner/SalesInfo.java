package com.github.sung.wxpay.v3.bean.request.inner;


import com.github.sung.wxcommon.bean.BaseV3Inner;
import com.github.sung.wxpay.v3.enums.SalesScenesTypeEnum;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.github.sung.wxcommon.annotation.Required;
import com.google.gson.annotations.SerializedName;
import lombok.*;
import org.apache.commons.lang3.StringUtils;

import java.util.List;


/**
 * 经营场景
 * 请根据实际经营情况，填写经营场景
 */

@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
public class SalesInfo extends BaseV3Inner {
    private static final long serialVersionUID = -3109479622358133073L;


    /**
     * 经营场景类型
     * sales_scenes_type
     * enum
     * 是
     */
    @Required
    @SerializedName("sales_scenes_type")
    private List<String> salesScenesType;

    /**
     * 线下门店场景
     * biz_store_info
     * object
     */
    @SerializedName("biz_store_info")
    private BizStoreInfo bizStoreInfo;

    /**
     * 公众号场景
     * mp_info
     * object
     */
    @SerializedName("mp_info")
    private MpInfo mpInfo;

    /**
     * 小程序场景
     * mini_program_info
     * object
     */
    @SerializedName("mini_program_info")
    private MiniProgramInfo miniProgramInfo;

    /**
     * APP场景
     * app_info
     * object
     */
    @SerializedName("app_info")
    private AppInfo appInfo;

    /**
     * 互联网网站场景
     * web_info
     * object
     */
    @SerializedName("web_info")
    private WebInfo webInfo;

    /**
     * 企业微信场景
     * wework_info
     * object
     */
    @SerializedName("wework_info")
    private WeworkInfo weworkInfo;

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BizStoreInfo extends BaseV3Inner {
        private static final long serialVersionUID = -1208786067401233225L;

        /**
         * 门店名称
         * biz_store_name
         * string(128)
         * 是
         */
        @Required
        @SerializedName("biz_store_name")
        private String bizStoreName;

        /**
         * 门店省市编码
         * biz_address_code
         * string(128)
         * 是
         */
        @Required
        @SerializedName("biz_address_code")
        private String bizAddressCode;

        /**
         * 门店地址
         * biz_store_address
         * string(128)
         * 是
         */
        @Required
        @SerializedName("biz_store_address")
        private String bizStoreAddress;

        /**
         * 门店门头照片
         * store_entrance_pic
         * string(1024)
         * 是
         */
        @Required
        @SerializedName("store_entrance_pic")
        private List<String> storeEntrancePic;

        /**
         * 店内环境照片
         * indoor_pic
         * string(1024)
         * 是
         */
        @Required
        @SerializedName("indoor_pic")
        private List<String> indoorPic;

        /**
         * 线下场所对应的商家APPID
         * biz_sub_appid
         * string(256)
         * 否
         */
        @SerializedName("biz_sub_appid")
        private String bizSubAppid;


        @Override
        public void checkConstraints() throws WxErrorException {

        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MpInfo extends BaseV3Inner {
        private static final long serialVersionUID = 8610586534860805485L;

        /**
         * 服务商公众号APPID
         * mp_appid
         * string(256)
         */
        @SerializedName("mp_appid")
        private String mpAppid;

        /**
         * 商家公众号APPID
         * mp_sub_appid
         * string(256)
         */
        @SerializedName("mp_sub_appid")
        private String mpSubAppid;

        /**
         * 公众号页面截图
         * mp_pics
         * string(1024)
         */
        @SerializedName("mp_pics")
        private List<String> mpPics;

        @Override
        public void checkConstraints() throws WxErrorException {
            if (StringUtils.isBlank(this.mpAppid) == StringUtils.isBlank(this.mpSubAppid)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "服务商公众号APPID与商家公众号APPID，二选一必填");
            }
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class MiniProgramInfo extends BaseV3Inner {
        private static final long serialVersionUID = 8793927050501798100L;

        /**
         * 服务商小程序APPID
         * mini_program_appid
         * string(256)
         */
        @SerializedName("mini_program_appid")
        private String miniProgramAppid;

        /**
         * 商家小程序APPID
         * mini_program_sub_appid
         * string(256)
         */
        @SerializedName("mini_program_sub_appid")
        private String miniProgramSubAppid;

        /**
         * 小程序截图
         * mini_program_pics
         * string(1024)
         */
        @SerializedName("mini_program_pics")
        private List<String> miniProgramPics;

        @Override
        public void checkConstraints() throws WxErrorException {
            if (StringUtils.isBlank(this.miniProgramAppid) == StringUtils.isBlank(this.miniProgramSubAppid)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "服务商小程序APPID与商家小程序APPID，二选一必填");
            }
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AppInfo extends BaseV3Inner {
        private static final long serialVersionUID = 9055687654190634738L;

        /**
         * 服务商应用APPID
         * app_appid
         * string(256)
         */
        @SerializedName("app_appid")
        private String appAppid;

        /**
         * 商家应用APPID
         * app_sub_appid
         * string(256)
         */
        @SerializedName("app_sub_appid")
        private String appSubAppid;

        /**
         * APP截图
         * app_pics
         * string(1024)
         * 是
         */
        @Required
        @SerializedName("app_pics")
        private List<String> appPics;


        @Override
        public void checkConstraints() throws WxErrorException {
            if (StringUtils.isBlank(this.appAppid) == StringUtils.isBlank(this.appSubAppid)) {
                throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "服务商应用APPID与商家应用APPID，二选一必填");
            }
        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WebInfo extends BaseV3Inner {
        private static final long serialVersionUID = 8662657918354298798L;

        /**
         * 互联网网站域名
         * domain
         * string(1024)
         * 是
         */
        @Required
        @SerializedName("domain")
        private String domain;

        /**
         * 网站授权函
         * web_authorisation
         * string(1024)
         * 否
         */
        @SerializedName("web_authorisation")
        private String webAuthorisation;

        /**
         * 互联网网站对应的商家APPID
         * web_appid
         * string(256)
         * 否
         */
        @SerializedName("web_appid")
        private String webAppid;

        @Override
        public void checkConstraints() throws WxErrorException {

        }
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    @Builder(builderMethodName = "newBuilder")
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WeworkInfo extends BaseV3Inner {
        private static final long serialVersionUID = 2354161307972018089L;

        /**
         * 商家企业微信CorpID
         * sub_corp_id
         * string(256)	是
         */
        @Required
        @SerializedName("sub_corp_id")
        private String subCorpId;

        /**
         * 企业微信页面截图
         * wework_pics
         * string(1024)
         * 是
         */
        @Required
        @SerializedName("wework_pics")
        private List<String> weworkPics;

        @Override
        public void checkConstraints() throws WxErrorException {

        }
    }

    @Override
    public void checkConstraints() throws WxErrorException {
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_STORE.name()) && this.bizStoreInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择“SALES_SCENES_STORE“，biz_store_info 必填");
        }
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_MP.name()) && this.mpInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择”SALES_SCENES_MP“，mp_info 必填");
        }
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_MINI_PROGRAM.name()) && this.miniProgramInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择”SALES_SCENES_MINI_PROGRAM“，mini_program_info 必填");
        }
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_APP.name()) && this.appInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择”SALES_SCENES_APP“，app_info 必填");
        }
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_WEB.name()) && this.webInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择”SALES_SCENES_WEB“，web_info 必填");
        }
        if (this.salesScenesType.contains(SalesScenesTypeEnum.SALES_SCENES_WEWORK.name()) && this.weworkInfo == null) {
            throw new WxErrorException(WxErrorExceptionFactor.INVALID_PARAMETER_CODE, "当“经营场景类型“选择”SALES_SCENES_WEWORK“，wework_info 必填");
        }
        if (this.bizStoreInfo != null) {
            this.bizStoreInfo.checkConstraints();
        }
        if (this.mpInfo != null) {
            this.mpInfo.checkConstraints();
        }
        if (this.miniProgramInfo != null) {
            this.miniProgramInfo.checkConstraints();
        }

        if (this.appInfo != null) {
            this.appInfo.checkConstraints();
        }

        if (this.webInfo != null) {
            this.webInfo.checkConstraints();
        }
        if (this.weworkInfo != null) {
            this.weworkInfo.checkConstraints();
        }
    }
}
