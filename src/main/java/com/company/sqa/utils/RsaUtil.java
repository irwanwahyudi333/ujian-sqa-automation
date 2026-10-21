package com.company.sqa.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;

public class RsaUtil {
    private static final Logger logger = LogManager.getLogger(RsaUtil.class);

    public static final String DEFAULT_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzMX6OysWzaizAsyFuabl" +
            "BfHUASPRRnqMosG/0dVIeOS9C2ckEotm5Z/aKEYUriYwuPqQOCm45VB+tpMChaBG" +
            "i6tFt4ytu3crvBKvctziIgsR+dy2qZJKWDqyxjCJgso/0S/XDOVKfMYbqJ4Cy/e0" +
            "nehBaage48nY2h5reEmrEOhMVbh+6izp31bQDYaA20/ouyuBTHwtLZdPsJOmSkVd" +
            "dNh7yHKJZqyHWMExyX+2Dvr4heCtIebanz5t5AHpeHvKLgIcgycfRv4wrKq4Lx3Pa" +
            "cJfXmW0QqAO3jSW+i9dF0zAWmAr2PlAUok47zZa11OBeePD15jeJgb2UMwR5p9gs" +
            "QIDAQAB";

    public static String encrypt(String plainText) {
        return encryptPassword(plainText, DEFAULT_PUBLIC_KEY);
    }

    public static String encryptPassword(String plainPassword) {
        return encryptPassword(plainPassword, DEFAULT_PUBLIC_KEY);
    }

    public static String encryptPassword(String plainPassword, String base64PublicKey) {
        try {
            byte[] keyBytes = Base64.getDecoder().decode(base64PublicKey.replaceAll("\\s+", ""));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = keyFactory.generatePublic(spec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            OAEPParameterSpec oaepParams = new OAEPParameterSpec(
                    "SHA-256",
                    "MGF1",
                    MGF1ParameterSpec.SHA256,
                    PSource.PSpecified.DEFAULT
            );
            cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams);

            byte[] encryptedBytes = cipher.doFinal(plainPassword.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            logger.error("Failed to encrypt password with RSA-OAEP: {}", e.getMessage(), e);
            throw new RuntimeException("RSA Encryption failed", e);
        }
    }
}
