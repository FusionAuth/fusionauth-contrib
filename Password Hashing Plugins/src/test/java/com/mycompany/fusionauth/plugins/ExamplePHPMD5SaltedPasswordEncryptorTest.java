package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Daniel DeGroff
 */
public class ExamplePHPMD5SaltedPasswordEncryptorTest {
  private static final String Base64 = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

  @Test(dataProvider = "hashes")
  public void encrypt(String hash, int expectedFactor, String expectedPassword, String expectedSalt, String plainText) {
    // Test control input and output to ensure the hash is correct.
    PasswordEncryptor encryptor = new ExamplePHPMD5SaltedPasswordEncryptor();

    // Assert factor calculation is correct
    int factor = 1 << Base64.indexOf(hash.charAt(3));
    assertEquals(expectedFactor, factor);

    // Assert the password extracted correctly
    String password = hash.substring(12);
    assertEquals(expectedPassword, password);

    // Assert the salt extracted correctly
    String salt = hash.substring(4, 12);
    assertEquals(expectedSalt, salt);

    // Assert the calculated hash
    String result = encryptor.encrypt(plainText, salt, factor);
    Assert.assertEquals(result, password);
  }

  @DataProvider(name = "hashes")
  public Object[][] hashes() {
    return new Object[][]{
        // These are example MD5 hashes you may find in your WordPress database if using phpass with MD5.
        // - In order to import these into FusionAuth you need to separate the password hash, salt and factor.
        //   See above example code for extracting these components from the hash found in WordPress.
        {"$P$9IQRaTwmfeRo7ud9Fh4E2PdI0S3r.L0", 2048, "eRo7ud9Fh4E2PdI0S3r.L0", "IQRaTwmf", "test12345"},
        {"$P$Buvd5FQxkeXJnDarN7..wRMwRKJeZ40", 8192, "eXJnDarN7..wRMwRKJeZ40", "uvd5FQxk", "!?!#?#?#$&@&@!&@"},
        {"$P$BVaXtDXwf/ceSVp8VpLKx8bS2Y4O5F/", 8192, "/ceSVp8VpLKx8bS2Y4O5F/", "VaXtDXwf", "123456"}
    };
  }
}
