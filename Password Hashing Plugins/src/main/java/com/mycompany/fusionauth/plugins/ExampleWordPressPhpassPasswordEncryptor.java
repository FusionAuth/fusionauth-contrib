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

import java.util.Random;
import java.util.Base64;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;
// import org.apache.commons.codec.binary.Base64;
import com.github.wolf480pl.phpass.PHPass;

public class ExampleWordPressPhpassPasswordEncryptor implements PasswordEncryptor {

  @Override
  public int defaultFactor() {
    return 8;
  }

  /**
   * @param password the plaintext password to encrypt
   * @param salt not the salt. rather give the full previous hashed password in its entirety.
   *             the salt is included in the hash. if this password has never been hashed before
   *             then leave salt as null.
   * @param factor should be 8 for wordpress
   * @return the encrypted password including the salt. salt does not need to be stored separately.
   */
  @Override
  public String encrypt(String password, String salt, int factor) {
    try {
        String previousHash = decode64(salt);
        PHPass phpass = new PHPass(factor);
        if (previousHash == null)
          return encode64(phpass.HashPassword(password));
        if (phpass.CheckPassword(decode64(password), previousHash))
          return encode64(previousHash);
        return encode64(getRandomPassword());
    }
    catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  String getRandomPassword() {
    return String.valueOf(new Random().nextInt(90000000) + 10000000);
  }

  String encode64(String s) {
    if (s == null) return null;
    return Base64.getEncoder().encodeToString(s.getBytes());
  }

  String decode64(String s) {
    if (s == null) return null;
    return new String(Base64.getDecoder().decode(s));
  }

}