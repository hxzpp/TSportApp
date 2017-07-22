package com.xinhuamm.xinhuasdk.util;

import android.util.Base64;

import java.security.GeneralSecurityException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * AES加密类
 */

public final class AES {
    private static final String AES_MODE = "AES/CBC/PKCS7Padding";

    public static String encryptByBase64(String content, String key, String iv)
            throws GeneralSecurityException {
        SecretKeySpec keysSpec = new SecretKeySpec(key.getBytes(), "AES");
        final Cipher cipher = Cipher.getInstance(AES_MODE);
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, keysSpec, ivSpec);
        byte[] cipherText = cipher.doFinal(content.getBytes());
        return Base64.encodeToString(cipherText, Base64.DEFAULT);
    }
}
