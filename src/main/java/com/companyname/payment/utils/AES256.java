package com.companyname.payment.utils;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@Slf4j
public class AES256 {

    static final byte[] iv = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private AES256() {
    }

    public static String encrypt(String strToEncrypt, String strSecretKey, String salt) {
        try {
            SecretKeySpec secretKey = getSecretKeySpec(strSecretKey, salt);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, getIvParameterSpec());
            return Base64.getEncoder()
                    .encodeToString(cipher.doFinal(strToEncrypt.getBytes(StandardCharsets.UTF_8)));
        } catch (Exception e) {
            log.error("Error while encrypting", e.fillInStackTrace());
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String strSecretKey, String salt) throws Exception {
        SecretKeySpec secretKey = getSecretKeySpec(strSecretKey, salt);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, getIvParameterSpec());
        return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
    }

    private static IvParameterSpec getIvParameterSpec() {
        return new IvParameterSpec(iv);
    }

    private static SecretKeySpec getSecretKeySpec(String strSecretKey, String salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        //Lower iteration count for performance.
        KeySpec spec = new PBEKeySpec(strSecretKey.toCharArray(), salt.getBytes(), 1000, 256);
        return new SecretKeySpec(factory.generateSecret(spec).getEncoded(), "AES");
    }
}
