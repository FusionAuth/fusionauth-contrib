/*
 * Copyright (c) 2019-2022, FusionAuth, All Rights Reserved
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

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;

/**
 * This is an example of a PBKDF2 HMAC SHA256 Salted hashing algorithm with a key length of 512. This is the default hashing algorithm for Keycloak.
 *
 * <p>
 * This code is provided to assist in your deployment and management of FusionAuth. Use of this
 * software is not covered under the FusionAuth license agreement and is provided "as is" without
 * warranty. https://fusionauth.io/license
 * </p>
 *
 * @author Dan Moore
 */
public class ExamplePBDKF2HMACSHA256KeyLength512PasswordHasher implements PasswordEncryptor {
  @Override
  public int defaultFactor() {
    return 10_000;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    if (factor <= 0) {
      throw new IllegalArgumentException("Invalid factor value [" + factor + "]");
    }

    SecretKeyFactory keyFactory;
    try {
      keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalStateException("No such algorithm [PBKDF2WithHmacSHA256]");
    }

    KeySpec keySpec = new PBEKeySpec(password.toCharArray(), Base64.getDecoder().decode(salt), factor, 512);
    SecretKey secret;
    try {
      secret = keyFactory.generateSecret(keySpec);
    } catch (InvalidKeySpecException e) {
      throw new IllegalArgumentException("Could not generate secret key for algorithm [PBKDF2WithHmacSHA256]");
    }

    byte[] encoded = secret.getEncoded();
    return new String(Base64.getEncoder().encode(encoded));
  }

//  @Override
//  public String pluginDisplayName() {
//    return "Super Awesome KeyCloak PBKDF2 512 Algorithm";
//  }
}
