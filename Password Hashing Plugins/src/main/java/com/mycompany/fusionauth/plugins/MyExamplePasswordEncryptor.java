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

/**
 * This example code is a starting point to build your own hashing algorithm in order to import users into FusionAuth.
 *
 * @author Daniel DeGroff
 */
public class MyExamplePasswordEncryptor implements PasswordEncryptor {
  @Override
  public int defaultFactor() {
    // TODO : Set the default factor for this encryptor. This value is used when one is not provided for a user.
    return 42;
  }

  @Override
  public String encrypt(String password, String salt, int factor) {
    System.out.println("Incoming Password + Salt: " + password + " | Salt: " + salt); 
    // Do encrypting type stuff here and then return the hash.

    // TODO : This is not a hash, it is 12345. But you should return a hash.
    return "12345";
  }
}
