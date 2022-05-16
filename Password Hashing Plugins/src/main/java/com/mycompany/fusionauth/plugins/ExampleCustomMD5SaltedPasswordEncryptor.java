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

import java.util.Base64;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;

/**
 * This is an example of a MD5 Salted hashing algorithm.
 *
 * <pre>{@code
 * hash = (salt + password)
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
public class ExampleCustomMD5SaltedPasswordEncryptor implements PasswordEncryptor {
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

    byte[] digest = (salt + password).getBytes(StandardCharsets.UTF_8);
    for (int i = 0; i < factor; i++) {
      digest = messageDigest.digest(digest);
    }

    return new String(Base64.getEncoder().encode(digest));
  }
}

