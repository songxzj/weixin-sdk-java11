package com.github.songxchn.wxpay.util;

import com.github.songxchn.common.exception.WxErrorException;
import com.github.songxchn.common.exception.WxErrorExceptionFactor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * apiv3 解密
 */
@Slf4j
public class DecryptUtils {

    private static final int TAG_LENGTH_BIT = 128;
    private static final String CIPHER_PROVIDER = "SunJCE";
    private static final String TRANSFORMATION_NoPadding = "AES/GCM/NoPadding";
    private static final String ALGORITHM = "AES";

    public static String decryptV3(String apiv3Key, String nonce, String associatedData, String cipherText) throws WxErrorException {
        try {
            final Cipher cipher = Cipher.getInstance(TRANSFORMATION_NoPadding);
            SecretKeySpec key = new SecretKeySpec(apiv3Key.getBytes(), ALGORITHM);
            GCMParameterSpec spec = new GCMParameterSpec(TAG_LENGTH_BIT, nonce.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, key, spec);
            cipher.updateAAD(associatedData.getBytes());
            return new String(cipher.doFinal(Base64Utils.decodeFromString(cipherText)));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new WxErrorException(WxErrorExceptionFactor.DECRYPT_CERTIFICATE_ERROR);
        }
    }
}
