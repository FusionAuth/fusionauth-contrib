package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Daniel DeGroff
 */
public class ExampleCustomMD5SaltedPasswordEncryptorTest {
  @Test
  public void encrypt() {
    // Test control input and output to ensure the hash is correct.
    PasswordEncryptor encryptor = new ExampleCustomMD5SaltedPasswordEncryptor();
    String result = encryptor.encrypt("password", "4MTVxbvk8swI0ys2Lf4saeR3swRvn0f2", 1);
    Assert.assertEquals(result, "4BmKaWmAdB7EnixWYV+8mA==");
  }
}
