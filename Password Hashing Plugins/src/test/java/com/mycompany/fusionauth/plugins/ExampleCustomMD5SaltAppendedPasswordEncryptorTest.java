package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Joshua O'Bannon
 */
public class ExampleCustomMD5SaltAppendedPasswordEncryptorTest {
  @Test
  public void encrypt() {
    // Test control input and output to ensure the hash is correct.
    PasswordEncryptor encryptor = new ExampleCustomMD5SaltAppendedPasswordEncryptor();

    // Random salt, base64 encoded.
    String encodedSalt =  "vywLzGDZgQgld4q3XtlBQo4QC1cf+ktM5BxMZPV7R+Q=";

    // Returns a hash based on our password, salt, and factor supplied.
    String result = encryptor.encrypt("password", encodedSalt, 1);
    Assert.assertEquals(result, "Nz6r9nFbQciP1fCeOx9Q1b8sC8xg2YEIJXeKt17ZQUKOEAtXH/pLTOQcTGT1e0fk");
  }
}
