package com.adminServer._core.security.encryption;

import com.adminServer._core.errors.exception.EncryptException;

public interface Encryption {

    String encrypt(String data) throws EncryptException;

    String decrypt(String encryptedData);
}
