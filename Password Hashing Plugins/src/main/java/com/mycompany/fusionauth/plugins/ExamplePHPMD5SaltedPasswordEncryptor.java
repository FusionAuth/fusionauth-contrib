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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.mycompany.fusionauth.util.Base64Tools;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;

/**
 * This is an example of a Portal PHP Password hashing algorithm.
 *
 * <pre>{@code
 * hash = (salt + password).getBytes()
 * }</pre>
 *
 * <p>
 * This code is provided to assist in your deployment and management of FusionAuth. Use of this
 * software is not covered under the FusionAuth license agreement and is provided "as is" without
 * warranty. https://fusionauth.io/license
 * </p>
 *
 * @author Daniel DeGroff
 */
public class ExamplePHPMD5SaltedPasswordEncryptor implements PasswordEncryptor {

  @Override
  public int defaultFactor() {
    return 1;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    if (factor <= 0) {
      throw new IllegalArgumentException("Invalid factor value [" + factor + "]");
    }

    MessageDigest messageDigest;
    try {
      messageDigest = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
      throw new IllegalArgumentException("No such algorithm [MD5]");
    }

    byte[] passwordBytes = password.getBytes(StandardCharsets.UTF_8);
    byte[] result = messageDigest.digest((salt + password).getBytes(StandardCharsets.UTF_8));

    for (int i = 0; i < factor; i++) {
      byte[] tmp = new byte[result.length + passwordBytes.length];
      System.arraycopy(result, 0, tmp, 0, result.length);
      System.arraycopy(passwordBytes, 0, tmp, result.length, passwordBytes.length);

      result = messageDigest.digest(tmp);
    }

    return Base64Tools.unixCryptEncodeToString(result, 16);
  }
}

