package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import com.mycompany.fusionauth.util.Base64Tools;

public class ExampleWordPressPhpassPasswordEncryptorTest {
  // Generated and self-checked by actual WordPress 7.1.2 wp_hash_password/wp_check_password.
  @DataProvider(name = "wordpressBcrypt")
  public Object[][] wordpressBcrypt() {
    return new Object[][] {
      {"migration-test-password", "$wp$2y$10$mdqtu8vUL5RhTRUVHLHP9OKaeqblzeqTNn4wxgRxUd5vA0P4Q9o1e"},
      {"pásswörd-東京-🔐", "$wp$2y$10$MSY8oB0Ms4NYvFFbyrsBIOOrN9MBF2V77DPlH9vPlRonW7xaXfYm."},
      {new String(new char[80]).replace('\0', 'a') + "correct-suffix", "$wp$2y$10$ASvUnSLCc5juw.uvmRgOYeV098wpyU/WUtIUpcC50NAwJf9Le/EnK"}
    };
  }

  @Test(dataProvider = "wordpressBcrypt")
  public void TestWordPressBcrypt(String password, String hash) {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String encoded = Base64Tools.encode64(hash);
    // The full stored hash, rather than factor, provides bcrypt's cost.
    Assert.assertEquals(encryptor.encrypt(password, encoded, 8), encoded);
    Assert.assertNotEquals(encryptor.encrypt(password + "-wrong", encoded, 8), encoded);
  }

  @Test
  public void TestLongPasswordSuffix() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    String encoded = Base64Tools.encode64("$wp$2y$10$ASvUnSLCc5juw.uvmRgOYeV098wpyU/WUtIUpcC50NAwJf9Le/EnK");
    String incorrect = new String(new char[80]).replace('\0', 'a') + "incorrect-suffix";
    Assert.assertNotEquals(encryptor.encrypt(incorrect, encoded, 8), encoded);
  }

  @Test
  public void TestInvalidWordPressBcrypt() {
    PasswordEncryptor encryptor = new ExampleWordPressPhpassPasswordEncryptor();
    for (String hash : new String[] {"$wp", "$wp$2y$10$short", "$wp$2x$10$" + new String(new char[53]).replace('\0', 'a'), "$wp$2y$99$" + new String(new char[53]).replace('\0', 'a')}) {
      String encoded = Base64Tools.encode64(hash);
      Assert.assertNotEquals(encryptor.encrypt("password", encoded, 8), encoded);
    }
    String encoded = Base64Tools.encode64("$wp$2y$10$mdqtu8vUL5RhTRUVHLHP9OKaeqblzeqTNn4wxgRxUd5vA0P4Q9o1e");
    Assert.assertNotEquals(encryptor.encrypt(new String(new char[2049]).replace('\0', 'é'), encoded, 8), encoded);
  }

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
