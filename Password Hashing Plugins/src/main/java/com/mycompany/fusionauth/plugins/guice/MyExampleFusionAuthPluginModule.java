/*
 * Copyright (c) 2020-2022, FusionAuth, All Rights Reserved
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
package com.mycompany.fusionauth.plugins.guice;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.MapBinder;
import com.mycompany.fusionauth.plugins.ExampleASPIdentityV2PasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleCustomMD5SaltAppendedPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleCustomMD5SaltedPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleFirebaseScryptPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExamplePBDKF2HMACSHA1PasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExamplePBDKF2HMACSHA256KeyLength512PasswordHasher;
import com.mycompany.fusionauth.plugins.ExamplePHPMD5SaltedPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleRfc2898DeriveBytesPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleSaltedSHA512PasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleStytchScryptPasswordEncryptor;
import com.mycompany.fusionauth.plugins.ExampleWordPressPhpassPasswordEncryptor;
import com.mycompany.fusionauth.plugins.MyExamplePasswordEncryptor;
import io.fusionauth.plugin.spi.PluginModule;
import io.fusionauth.plugin.spi.security.PasswordEncryptor;

/**
 * @author Daniel DeGroff
 */
@PluginModule
public class MyExampleFusionAuthPluginModule extends AbstractModule {
  @Override
  protected void configure() {
    MapBinder<String, PasswordEncryptor> passwordEncryptorMapBinder = MapBinder.newMapBinder(binder(), String.class, PasswordEncryptor.class);

    // TODO :
    //   1. Add one or more bindings here
    //   2. Name your binding. This will be the value you set in the 'encryptionScheme' on the user to utilize this encryptor.
    //      - Note, as a best practice, namespace your plugin with a prefix such as 'custom-' to avoid any future conflicts with new
    //        hashing algorithms that FusionAuth may ship in the future. For example, your 'custom-argon2id'.
    //   3. Delete any example code you don't use and do not want in your plugin. In addition to the bindings, you should delete any corresponding classes and tests you do not use in your plugin.

    // Start with this example and implement it.
    passwordEncryptorMapBinder.addBinding("example-hash").to(MyExamplePasswordEncryptor.class);


    //
    // Below are Functional examples
    //
    passwordEncryptorMapBinder.addBinding("example-custom-md5").to(ExampleCustomMD5SaltedPasswordEncryptor.class);
    passwordEncryptorMapBinder.addBinding("example-salt-appended-md5").to(ExampleCustomMD5SaltAppendedPasswordEncryptor.class);
    passwordEncryptorMapBinder.addBinding("example-salted-sha512").to(ExampleSaltedSHA512PasswordEncryptor.class);

    // Example of a PHP MD5 Salted Password hasher. This is the MD5 implementation from the Portal PHP Password hashing framework. (phpass)
    passwordEncryptorMapBinder.addBinding("example-phpass-md5").to(ExamplePHPMD5SaltedPasswordEncryptor.class);

    // /atg/dynamo/security/PBKDF2PasswordHasher-10000
    passwordEncryptorMapBinder.addBinding("example-salted-pbkdf2-hmac-sha1-10000").to(ExamplePBDKF2HMACSHA1PasswordEncryptor.class);

    // Rfc2898DeriveBytes
    // https://github.com/aspnet/AspNetIdentity/blob/master/src/Microsoft.AspNet.Identity.Core/Crypto.cs#L26
    passwordEncryptorMapBinder.addBinding("example-Rfc2898DeriveBytes").to(ExampleRfc2898DeriveBytesPasswordEncryptor.class);

    // Argon2id
    // https://github.com/P-H-C/phc-winner-argon2
    // passwordEncryptorMapBinder.addBinding("example-argon2id").to(ExampleArgon2idPasswordEncryptor.class);

    passwordEncryptorMapBinder.addBinding("example-keycloak").to(ExamplePBDKF2HMACSHA256KeyLength512PasswordHasher.class);
    passwordEncryptorMapBinder.addBinding("example-salted-pbkdf2-hmac-sha1-10000").to(ExamplePBDKF2HMACSHA1PasswordEncryptor.class);

    // Example Firebase Scrypt
    passwordEncryptorMapBinder.addBinding("example-salted-firebase-scrypt").to(ExampleFirebaseScryptPasswordEncryptor.class);

    // Example ASP Identity V2 Hasher
    passwordEncryptorMapBinder.addBinding("example-asp-identity-v2").to(ExampleASPIdentityV2PasswordEncryptor.class);

    // Example Stytch Scrypt
    passwordEncryptorMapBinder.addBinding("example-salted-stytch-scrypt").to(ExampleStytchScryptPasswordEncryptor.class);

    // Example Wordpress Scrypt
    passwordEncryptorMapBinder.addBinding("example-wordpress-phpass").to(ExampleWordPressPhpassPasswordEncryptor.class);
  }
}
