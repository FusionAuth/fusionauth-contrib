/*
 * Copyright (c) 2023, FusionAuth, All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 */
package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKeyFactory;
import javax.crypto.SecretKey;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.util.Base64;

/**
 * This example code is a starting point to build your own hashing algorithm in order to import users into FusionAuth.
 */
public class ExampleASPIdentityV2PasswordEncryptor implements PasswordEncryptor {

  @Override
  public int defaultFactor() {
    return 1000;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    try {     
        byte[] saltBytes = Base64.getDecoder().decode(salt.getBytes());
        char[] passwordChars = password.toCharArray();
        SecretKeyFactory skf = SecretKeyFactory.getInstance( "PBKDF2WithHmacSHA1" );
        PBEKeySpec spec = new PBEKeySpec( passwordChars, saltBytes, factor, 32*8 );
        SecretKey key = skf.generateSecret( spec );
        byte[] result = key.getEncoded();
        String hash64 = Base64.getEncoder().encodeToString(result);
        return hash64;
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
  }
}
