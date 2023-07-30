package com.adminServer._core.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESEncryptionUtil {
    private static final String AES_ALGORITHM = "AES";
    private static final String AES_KEY = "mysecretaeskey123"; // 이 부분은 실제로 사용 시에는 보안에 강한 랜덤 키를 사용해야 합니다.

    public static String encrypt(String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(AES_KEY.getBytes(), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        byte[] encryptedData = cipher.doFinal(data.getBytes());
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(AES_KEY.getBytes(), AES_ALGORITHM);
        Cipher cipher = Cipher.getInstance(AES_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        byte[] decryptedData = Base64.getDecoder().decode(encryptedData);
        byte[] originalData = cipher.doFinal(decryptedData);

        return new String(originalData);
    }
}
