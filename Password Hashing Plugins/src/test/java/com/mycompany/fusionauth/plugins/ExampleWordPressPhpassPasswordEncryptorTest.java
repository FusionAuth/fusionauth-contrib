package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.mycompany.fusionauth.util.Base64Tools;

public class ExampleWordPressPhpassPasswordEncryptorTest {
  @Test
  public void TestExistingHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami", Base64Tools.encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8); // in base 64: JFAkQlZyZHNXL05VdVhEaTBPZDB1VWRrMlNuSkhIbVEwMQ==
    Assert.assertEquals(Base64Tools.decode64(result), "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
  }

  @Test
  public void TestExistingHashFails() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami2", Base64Tools.encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8);
    String result2 = encryptor.encrypt("bitnami2", Base64Tools.encode64("$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01"), 8);
    Assert.assertNotEquals(Base64Tools.decode64(result), "$P$BVrdsW/NUuXDi0Od0uUdk2SnJHHmQ01");
    Assert.assertEquals(Base64Tools.decode64(result).length(), 8);
    Assert.assertNotEquals(Base64Tools.decode64(result), Base64Tools.decode64(result2));
  }

  @Test
  public void TestNewHash() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String result = encryptor.encrypt("bitnami", null, 8);
    Assert.assertTrue(Base64Tools.decode64(result).startsWith("$P$B"));
    Assert.assertEquals(Base64Tools.decode64(result).length(), 34);
  }

}