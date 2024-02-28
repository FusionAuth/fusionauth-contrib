package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ExampleWordPressPhpassPasswordEncryptorTest {
  @Test
  public void TestExistingHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami", "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01", 8);
    Assert.assertEquals(result, "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
  }

  @Test
  public void TestExistingHashFails() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami2", "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01", 8);
    String result2 = encryptor.encrypt("bitnami2", "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01", 8);
    Assert.assertNotEquals(result, "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
    Assert.assertEquals(result.length(), 8);
    Assert.assertNotEquals(result, result2);
  }

  @Test
  public void TestNewHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami", null, 8);
    Assert.assertTrue(result.startsWith("$P$B"));
    Assert.assertEquals(result.length(), 34);
  }
}