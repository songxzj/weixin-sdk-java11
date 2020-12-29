package com.github.sung.wxpay.util;


import com.github.sung.wxcommon.exception.WxErrorException;
import com.github.sung.wxcommon.exception.WxErrorExceptionFactor;
import com.github.sung.wxpay.constant.WxPayConstants;
import com.github.sung.wxpay.v2.bean.cert.WxPayCertificate;
import com.github.sung.wxpay.v3.bean.cert.WxPayV3Certificate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Slf4j
public class CertKeyUtils {

    private static final int TAG_LENGTH_BIT = 128;
    private static final String CIPHER_PROVIDER = "SunJCE";
    private static final String TRANSFORMATION_NoPadding = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES";


    /**
     * 平台证书解密 （v2）
     *
     * @param apiv3Key
     * @return
     * @throws Exception
     */
    public static WxPayCertificate decryptCertificate(String apiv3Key, WxPayCertificate wxPayCertificate) throws WxErrorException {
        try {
            WxPayCertificate.EncryptCertificate encryptCertificate = wxPayCertificate.getEncryptCertificate();

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION_NoPadding, CIPHER_PROVIDER);
            SecretKeySpec key = new SecretKeySpec(apiv3Key.getBytes(), ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, encryptCertificate.getNonce().getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(encryptCertificate.getAssociatedData().getBytes());
            wxPayCertificate.setCertificateStr(new String(cipher.doFinal(Base64Utils.decodeFromString(encryptCertificate.getCipherText()))));
            return wxPayCertificate;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.DECRYPT_CERTIFICATE_ERROR);
        }
    }

    /**
     * 平台证书解密 （v3）
     *
     * @param apiv3Key
     * @param wxPayV3Certificate
     * @return
     * @throws WxErrorException
     */
    public static WxPayV3Certificate decryptV3Certificate(String apiv3Key, WxPayV3Certificate wxPayV3Certificate) throws WxErrorException {
        try {
            WxPayV3Certificate.EncryptV3Certificate encryptV3Certificate = wxPayV3Certificate.getEncryptV3Certificate();

            final Cipher cipher = Cipher.getInstance(TRANSFORMATION_NoPadding);
            SecretKeySpec key = new SecretKeySpec(apiv3Key.getBytes(), ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, encryptV3Certificate.getNonce().getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(encryptV3Certificate.getAssociatedData().getBytes());
            wxPayV3Certificate.setCertificateStr(new String(cipher.doFinal(Base64Utils.decodeFromString(encryptV3Certificate.getCipherText()))));
            return wxPayV3Certificate;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.DECRYPT_CERTIFICATE_ERROR);
        }
    }

    /**
     * 加载 v3 商户密钥
     *
     * @param inputStream
     * @return
     * @throws WxErrorException
     */
    public static PrivateKey loadPrivateKey(InputStream inputStream) throws WxErrorException {
        try {
            ByteArrayOutputStream array = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                array.write(buffer, 0, length);
            }
            String privateKeyStr = array.toString(WxPayConstants.DEFAULT_CHARSET);
            return loadPrivateKey(privateKeyStr);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.KEY_ERROR_ERROR);
        }
    }

    /**
     * 加载 v3 商户密钥
     *
     * @param privateKeyStr
     * @return
     * @throws WxErrorException
     */
    public static PrivateKey loadPrivateKey(String privateKeyStr) throws WxErrorException {
        try {
            String privateKey = privateKeyStr
                    .replace("-----BEGIN PRIVATE KEY-----", "")
                    .replace("-----END PRIVATE KEY-----", "")
                    .replaceAll("\\s+", "");

            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(new PKCS8EncodedKeySpec(Base64Utils.decodeFromString(privateKey)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.KEY_ERROR_ERROR);
        }
    }

    /**
     * 加载证书
     *
     * @param inputStream
     * @return
     * @throws WxErrorException
     */
    public static X509Certificate loadCertificate(InputStream inputStream) throws WxErrorException {
        try {
            CertificateFactory cf = CertificateFactory.getInstance("X509");
            X509Certificate cert = (X509Certificate) cf.generateCertificate(inputStream);
            cert.checkValidity();
            return cert;
        } catch (CertificateException e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.KEY_FILE_ERROR);
        }
    }

    /**
     * 加载证书
     *
     * @param certificateStr
     * @return
     * @throws WxErrorException
     */
    public static X509Certificate loadCertificate(String certificateStr) throws WxErrorException {
        return loadCertificate(new ByteArrayInputStream(certificateStr.getBytes()));
    }

}
