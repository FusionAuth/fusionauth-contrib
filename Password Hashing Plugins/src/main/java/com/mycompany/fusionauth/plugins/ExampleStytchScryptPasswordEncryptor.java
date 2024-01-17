/*
 * Copyright (c) 2020, FusionAuth, All Rights Reserved
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
import com.lambdaworks.crypto.SCrypt;
import org.apache.commons.codec.binary.Base64;
import javax.crypto.Cipher; // https://docs.oracle.com/en/java/javase/11/docs/api/java.base/javax/crypto/Cipher.html
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;
import java.security.Key;
import java.security.MessageDigest;

/**
 * This example code is a starting point to build your own hashing algorithm in order to import users into FusionAuth.
 */
public class ExampleStytchScryptPasswordEncryptor implements PasswordEncryptor {

  private static final Charset Charset = StandardCharsets.US_ASCII;
  private static final String CipherTransformation = "AES/CTR/NoPadding";

// tag::scryptParameters[]
  /* Scrypt Parameters. You can find the correct settings for your Stytch project
    in the email they sent you containing your hashes. Copy them here. */
  private static final int MemoryCost = 1 << 15;
  private static final int BlockSize = 8;
  private static final int Parallelization = 1;
  private static final int KeyLength = 32;
// end::scryptParameters[]

  @Override
  public int defaultFactor() {
    return 0;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    try {
        // concatenating decoded salt + separator
        // byte[] decodedSaltBytes = Base64.decodeBase64(salt.getBytes(Charset));
        //byte[] decodedSaltSepBytes = Base64.decodeBase64(saltSep.getBytes(Charset));
        // byte[] saltConcat = new byte[decodedSaltBytes.length + decodedSaltSepBytes.length];
        // System.arraycopy(decodedSaltBytes, 0, saltConcat, 0, decodedSaltBytes.length);
        // System.arraycopy(decodedSaltSepBytes, 0, saltConcat, decodedSaltBytes.length, decodedSaltSepBytes.length);

        // hashing password
        byte[] hashedBytes =  SCrypt.scrypt(password.getBytes(Charset), salt.getBytes(Charset), MemoryCost, factor, Parallelization, 64);

        // encrypting with aes
        // byte[] signerBytes = Base64.decodeBase64(base64_signer_key.getBytes(Charset));
        // byte[] cipherTextBytes = encrypt(signerBytes, hashedBytes);
        // return new String(Base64.encodeBase64(cipherTextBytes));
        new String(Base64.encodeBase64(hashedBytes));
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}