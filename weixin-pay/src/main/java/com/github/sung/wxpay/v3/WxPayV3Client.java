package com.github.sung.wxpay.v3;


import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.github.sung.wxpay.util.CertKeyUtils;
import com.github.sung.wxpay.util.SignUtils;
import com.github.sung.wxpay.v3.bean.request.BaseWxPayV3Request;
import com.github.sung.wxpay.v3.bean.request.WxCertificatesV3Request;
import com.github.sung.wxpay.v3.bean.request.media.WxMediaUploadV3Request;
import com.github.sung.wxpay.v3.bean.result.BaseWxPayV3Result;
import com.github.sung.wxpay.v3.bean.result.WxCertificatesV3Result;
import com.github.sung.wxpay.v3.bean.result.media.WxMediaUploadV3Result;
import com.github.sung.wxpay.v3.bean.cert.WxPayV3Certificate;
import com.github.sung.wxpay.constant.WxPayConstants;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.*;
import org.springframework.http.client.OkHttp3ClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.nio.charset.Charset;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.List;


@Slf4j
public class WxPayV3Client {

    /**
     * 微信支付接口请求地址域名部分.
     */
    private String serverUrl = WxPayConstants.DEFAULT_PAY_BASE_URL;

    /**
     * http请求连接超时时间.
     */
    private int connectTimeout = 5000;

    /**
     * http请求数据读取等待时间.
     */
    private int readTimeout = 10000;

    /**
     * 返回所设置的微信支付接口请求地址域名.
     *
     * @return 微信支付接口请求地址域名
     */
    private String getServerUrl() {
        return this.serverUrl;
    }

    private String getSchema() {
        return "WECHATPAY2-SHA256-RSA2048";
    }

    /**
     * 商户号.
     */
    private final String mchId;

    /**
     * 商户API证书序列号
     */
    private final String serialNo;

    /**
     * 微信支付平台证书序列号
     */
    private String wxSerialNo;


    /**
     * 商户私钥.
     */
    private final PrivateKey privateKey;

    /**
     * 平台公钥
     */
    private String wxCertificateStr;

    /**
     * apiv3 密钥
     */
    private final String apiv3Key;

    public String getWxSerialNo() {
        return wxSerialNo;
    }

    public String getWxCertificateStr() {
        return wxCertificateStr;
    }

    public WxPayV3Client(String mchId, String serialNo, String wxSerialNo, String privateKey, String wxCertificateStr, String apiv3Key) throws WxErrorException {
        this.mchId = mchId;
        this.serialNo = serialNo;
        this.wxSerialNo = wxSerialNo;
        this.privateKey = CertKeyUtils.loadPrivateKey(privateKey);
        this.wxCertificateStr = wxCertificateStr;
        this.apiv3Key = apiv3Key;
    }

    // 仅用于不需要平台证书包含在HTTP头的时候
    public WxPayV3Client(String mchId, String serialNo, String privateKey, String apiv3Key) throws WxErrorException {
        this(mchId, serialNo, null, privateKey, null, apiv3Key);
    }


    /**
     * 通用执行方法
     *
     * @param request
     * @param <T>
     * @return
     * @throws WxErrorException
     */
    public <T extends BaseWxPayV3Result> T execute(BaseWxPayV3Request<T> request) throws WxErrorException {
        String token = checkAndSignAndGetToken(request);
        String requestUrl = getServerUrl() + request.routing();
        String responseContent = restExchange(requestUrl, request, token);

        return BaseWxPayV3Result.fromJson(responseContent, request.getResultClass());
    }


    /**
     * 检查参数，并设置签名.
     * 1、检查参数（注意：子类实现需要检查参数的而外功能时，请在调用父类的方法前进行相应判断）
     * 2、生成签名
     * 3、生成 http 头认证
     * </pre>
     *
     * @throws WxErrorException the wx pay exception
     */
    private <T extends BaseWxPayV3Result> String checkAndSignAndGetToken(BaseWxPayV3Request<T> request) throws WxErrorException {
        request.checkFields();

        long timestamp = System.currentTimeMillis() / 1000;
        String nonceStr = RandomUtil.randomString(32);

        StringBuilder toSign = new StringBuilder();
        toSign.append(request.getHttpMethod().name()).append("\n")
                .append(request.routing()).append("\n")
                .append(timestamp).append("\n")
                .append(nonceStr).append("\n")
                .append(request.toSignString()).append("\n");


        String signature = SignUtils.createSHA256withRSASign(toSign.toString(), this.privateKey);

        StringBuilder token = new StringBuilder();
        token.append("mchid=\"").append(this.mchId).append("\",")
                .append("serial_no=\"").append(this.serialNo).append("\",")
                .append("nonce_str=\"").append(nonceStr).append("\",")
                .append("timestamp=\"").append(timestamp).append("\",")
                .append("signature=\"").append(signature).append("\"");

        return token.toString();
    }

    private <T extends BaseWxPayV3Result> String restExchange(String requestUrl, BaseWxPayV3Request<T> request, String token) throws WxErrorException {
        boolean hasError = false;
        String requestHeaderStr = null;
        String requestContent = request.toJsonString();
        String responseHeaderStr = null;
        HttpStatus httpStatus = null;
        String responseContent = null;
        long begin = System.currentTimeMillis();
        try {
            RestTemplate restClient = getRestClient();
            HttpHeaders requestHeaders = getRequestHeaders(token);
            requestHeaderStr = requestHeaders.toString();

            HttpEntity<String> requestEntity = new HttpEntity<>(requestContent, requestHeaders);
            ResponseEntity<String> responseEntity = restClient.exchange(requestUrl, request.getHttpMethod(), requestEntity, String.class);

            httpStatus = responseEntity.getStatusCode();
            HttpHeaders responseHeaders = responseEntity.getHeaders();
            responseContent = responseEntity.getBody();

            responseHeaderStr = responseHeaders.toString();

            // 校验签名
            if (request.isCheckSign()) {
                checkResult(responseHeaders, responseContent);
            }

            return responseContent;
        } catch (Exception e) {
            String errMsg = e.getMessage();
            if (e instanceof HttpStatusCodeException) {
                HttpStatusCodeException e1 = (HttpStatusCodeException) e;
                httpStatus = e1.getStatusCode();
                errMsg = e1.getMessage() + e1.getResponseBodyAsString();
            }
            log.error(errMsg, e);
            hasError = true;
            throw new WxErrorException(WxErrorExceptionFactor.HTTP_REQUEST_FAIL_CODE, errMsg);
        } finally {
            log.warn("wxpay url: {}\n" +
                            "request header: \n{}\n" +
                            "request content: \n{}\n" +
                            "wxpay request {}, cost time: {}, http status code: {}\n" +
                            "response header: \n{}\n" +
                            "response content: \n{}",
                    requestUrl, requestHeaderStr, requestContent, hasError ? "failed" : "succeeded", System.currentTimeMillis() - begin, httpStatus, responseHeaderStr, responseContent);
        }
    }

    /**
     * 图片上传特定
     *
     * @param request
     * @return
     * @throws WxErrorException
     */
    public WxMediaUploadV3Result uploadMedia(WxMediaUploadV3Request request) throws WxErrorException {
        String sha256;
        File file = request.getFile();
        try {
            InputStream data = new FileInputStream(file);
            request.setFilename(file.getName());
            sha256 = DigestUtil.sha256Hex(data);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.FILE_ERROR);
        }
        request.setSha256(sha256);

        String token = checkAndSignAndGetToken(request);
        String requestUrl = getServerUrl() + request.routing();

        boolean hasError = false;
        String requestHeaderStr = null;
        String responseHeaderStr = null;
        HttpStatus httpStatus = null;
        String responseContent = null;
        long begin = System.currentTimeMillis();
        try {
            RestTemplate restClient = getRestClient();
            HttpHeaders requestHeaders = new HttpHeaders();
            requestHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.MULTIPART_FORM_DATA_VALUE);
            requestHeaders.set("Authorization", getSchema() + " " + token);
            ByteArrayOutputStream output = getRequestByteArray(request);

            requestHeaderStr = requestHeaders.toString();

            HttpEntity<byte[]> requestEntity = new HttpEntity<>(output.toByteArray(), requestHeaders);
            ResponseEntity<String> responseEntity = restClient.exchange(requestUrl, request.getHttpMethod(), requestEntity, String.class);

            httpStatus = responseEntity.getStatusCode();
            HttpHeaders responseHeaders = responseEntity.getHeaders();
            responseContent = responseEntity.getBody();

            responseHeaderStr = responseHeaders.toString();

            // 校验签名
            if (request.isCheckSign()) {
                checkResult(responseHeaders, responseContent);
            }

            return BaseWxPayV3Result.fromJson(responseContent, request.getResultClass());
        } catch (Exception e) {
            String errMsg = e.getMessage();
            if (e instanceof HttpStatusCodeException) {
                HttpStatusCodeException e1 = (HttpStatusCodeException) e;
                httpStatus = e1.getStatusCode();
                errMsg = e1.getMessage() + e1.getResponseBodyAsString();
            }
            log.error(errMsg, e);
            hasError = true;
            throw new WxErrorException(WxErrorExceptionFactor.HTTP_REQUEST_FAIL_CODE, errMsg);
        } finally {
            log.warn("wxpay url: {}\n" +
                            "request header: \n{}\n" +
                            "wxpay request {}, cost time: {}, http status code: {}\n" +
                            "response header: \n{}\n" +
                            "response content: \n{}",
                    requestUrl, requestHeaderStr, hasError ? "failed" : "succeeded", System.currentTimeMillis() - begin, httpStatus, responseHeaderStr, responseContent);
        }

    }

    private ByteArrayOutputStream getRequestByteArray(WxMediaUploadV3Request request) throws WxErrorException {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("--boundary").append("\r\n")
                .append("Content-Disposition: form-data; name=\"meta\";").append("\r\n")
                .append("Content-Type: application/json").append("\r\n")
                .append("\r\n")
                .append(request.toJsonString()).append("\r\n")
                .append("--boundary").append("\r\n")
                .append("Content-Disposition: form-data; name=\"file\"; filename=\"").append(request.getFilename()).append("\";").append("\r\n");
        if (request.isImageFile()) {
            sb1.append("Content-Type: image/jpg");
        } else if (request.isVideoFile()) {
            sb1.append("Content-Type: video/mp4");
        } else {
            throw new WxErrorException(WxErrorExceptionFactor.FILE_ERROR);
        }
        sb1.append("\r\n")
                .append("\r\n");

        StringBuffer sb2 = new StringBuffer();
        sb2.append("\r\n")
                .append("--boundary--").append("\r\n");

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            output.write(sb1.toString().getBytes());
            output.write(IOUtils.toByteArray(new FileInputStream(request.getFile())));
            output.write(sb2.toString().getBytes());
            return output;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.FILE_ERROR);
        }
    }


    /**
     * 验签
     *
     * @param responseHeaders
     * @param responseContent
     * @throws WxErrorException
     */
    private void checkResult(HttpHeaders responseHeaders, String responseContent) throws WxErrorException {
        String responseTimestamp = responseHeaders.getFirst("Wechatpay-Timestamp");
        String responseNonce = responseHeaders.getFirst("Wechatpay-Nonce");
        String responseSignature = responseHeaders.getFirst("Wechatpay-Signature");
        String responseSerial = responseHeaders.getFirst("Wechatpay-Serial");

        if (StringUtils.isAnyBlank(responseTimestamp, responseNonce, responseSignature, responseSerial)) {
            throw new WxErrorException(WxErrorExceptionFactor.CHECK_SIGN_ERROR);
        }
        if (StringUtils.isBlank(responseContent)) {
            responseContent = "";
        }

        StringBuilder toSign = new StringBuilder();
        toSign.append(responseTimestamp).append("\n")
                .append(responseNonce).append("\n")
                .append(responseContent).append("\n");

        getV3Certificate(responseSerial);

        X509Certificate certificate = CertKeyUtils.loadCertificate(this.wxCertificateStr);

        if (!SignUtils.checkSHA256withRSASign(certificate, toSign.toString(), responseSignature)) {
            throw new WxErrorException(WxErrorExceptionFactor.CHECK_SIGN_ERROR);
        }
    }

    /**
     * 根据返回的平台序列号去查询平台证书
     *
     * @param responseWxSerialNo
     * @return
     * @throws WxErrorException
     */
    private void getV3Certificate(String responseWxSerialNo) throws WxErrorException {
        if (!StringUtils.isBlank(responseWxSerialNo) && responseWxSerialNo.equals(this.wxSerialNo)) {
            return;
        }
        WxCertificatesV3Request request = WxCertificatesV3Request.newBuilder().build();
        WxCertificatesV3Result result = execute(request);
        List<WxPayV3Certificate> wxPayV3CertificateList = result.getWxPayV3CertificateList();
        WxPayV3Certificate wxPayV3Certificate = wxPayV3CertificateList.get(0);
        if (!StringUtils.isBlank(responseWxSerialNo)) {
            for (WxPayV3Certificate temp : wxPayV3CertificateList) {
                if (wxPayV3Certificate.getSerialNo().equals(responseWxSerialNo)) {
                    wxPayV3Certificate = temp;
                    break;
                }
            }
        }
        wxPayV3Certificate = CertKeyUtils.decryptV3Certificate(this.apiv3Key, wxPayV3Certificate);
        this.wxSerialNo = wxPayV3Certificate.getSerialNo();
        this.wxCertificateStr = wxPayV3Certificate.getCertificateStr();
    }

    /**
     * 获取 RestTemplate
     *
     * @return
     * @throws WxErrorException
     */
    private RestTemplate getRestClient() throws WxErrorException {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        OkHttpClient okHttpClient = builder.build();
        OkHttp3ClientHttpRequestFactory requestFactory = new OkHttp3ClientHttpRequestFactory(okHttpClient);
        requestFactory.setConnectTimeout(this.connectTimeout);
        requestFactory.setReadTimeout(this.readTimeout);

        RestTemplate restClient = new RestTemplate(requestFactory);
        restClient.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName(WxPayConstants.DEFAULT_CHARSET)));

        return restClient;
    }

    /**
     * 获取请求头
     *
     * @param token
     * @return
     * @throws WxErrorException
     */
    private HttpHeaders getRequestHeaders(String token) throws WxErrorException {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_UTF8_VALUE);
        requestHeaders.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        requestHeaders.set("Authorization", getSchema() + " " + token);
        if (!StringUtils.isBlank(this.wxSerialNo)) {
            requestHeaders.set("Wechatpay-Serial", this.wxSerialNo);
        }
        return requestHeaders;
    }

}
