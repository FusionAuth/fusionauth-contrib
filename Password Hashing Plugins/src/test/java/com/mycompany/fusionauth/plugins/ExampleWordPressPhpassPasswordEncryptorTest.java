package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.Base64;

public class ExampleWordPressPhpassPasswordEncryptorTest {
  @Test
  public void TestExistingHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt(encode64("bitnami"), encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8);
    Assert.assertEquals(decode64(result), "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
  }

  @Test
  public void TestExistingHashFails() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt(encode64("bitnami2"), encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8);
    String result2 = encryptor.encrypt(encode64("bitnami2"), encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8);
    Assert.assertNotEquals(decode64(result), "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
    Assert.assertEquals(decode64(result).length(), 8);
    Assert.assertNotEquals(decode64(result), decode64(result2));
  }

  @Test
  public void TestNewHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt(encode64("bitnami"), null, 8);
    Assert.assertTrue(decode64(result).startsWith("$P$B"));
    Assert.assertEquals(decode64(result).length(), 34);
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