package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Daniel DeGroff
 */
public class ExampleSaltedSHA512PasswordEncryptorTest {
  @Test
  public void encrypt() {
    // Test control input and output to ensure the hash is correct.
    PasswordEncryptor encryptor = new ExampleSaltedSHA512PasswordEncryptor();
    String result = encryptor.encrypt("password", "4MTVxbvk8swI0ys2Lf4saeR3swRvn0f2", 1);
    Assert.assertEquals(result, "GohyH5x4BaWvBhSNW4yTctmxY7azS7wqDOIENW1uOUHt6Qd2kDAimYweM9F95TH82OJHZIdm6kYirCtHpUdsIA==");
  }
}
