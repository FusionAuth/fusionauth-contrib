package com.mycompany.fusionauth.plugins;

import java.util.Base64;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author Daniel DeGroff
 */
public class ExampleRfc2898DeriveBytesPasswordEncryptorTest {
  @Test(dataProvider = "hashes")
  public void encrypt(String password, String hash) {
    // Extract the salt and hash from the monolithic hash
    byte[] bytes = Base64.getDecoder().decode(hash.getBytes());

    // See https://github.com/aspnet/AspNetIdentity/blob/master/src/Microsoft.AspNet.Identity.Core/Crypto.cs#L26
    byte[] salt = new byte[16];
    byte[] subKey = new byte[32];

    System.arraycopy(bytes, 1, salt, 0, 16);
    System.arraycopy(bytes, 1 + 16, subKey, 0, 32);

    String encodedSalt = Base64.getEncoder().encodeToString(salt);
    String encodedHash = Base64.getEncoder().encodeToString(subKey);

    PasswordEncryptor encryptor = new ExampleRfc2898DeriveBytesPasswordEncryptor();
    assertEquals(encryptor.encrypt(password, encodedSalt, 1_000), encodedHash);
  }

  @DataProvider(name = "hashes")
  public Object[][] hashes() {
    return new Object[][]{
        {"supersecret", "AJERV+ZvwakXg+AP09yPDJtwucf/9+jsoAGoeB8CMTzr0bsEQL3IQ4StQ/8/EmwHFA=="},
        {"supersecret", "AEhNvtxbnvkRnAEPRS6ol0Edi+WF6AtXOwDk3Eck/dfeUpCulPX1yVU4G9UpFRycjQ=="},
        {"supersecret", "AI0060+guxTYvJPL3kEqAzDwW3Avnpbd7+lTXM/K8ft7433JMy1PS/pSmhBIX3xotw=="},
        {"supersecret", "AM0P7hfdDHw2d0Y9lZoxN9FY5v7rn8cfEL1AGngrNmFxZLPZs832m+lWPlPIepyuoQ=="},
        {"supersecret", "AGlXna8THOKvQJGBFentzM11DRO65gdWYwrUM9G4iFKY2DFt4qFc1/O/zaFTEikaOA=="},
    };
  }
}
