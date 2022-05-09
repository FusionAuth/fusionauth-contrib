package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Daniel DeGroff
 */
public class MyExamplePasswordEncryptorTest {
  @Test
  public void encrypt() {
    // TODO : Assert that a plain text password matches an expected hash.
    // - This example code will assert correctly based upon the implementation in 'MyExamplePasswordEncryptor'
    PasswordEncryptor encryptor = new MyExamplePasswordEncryptor();
  }
}
