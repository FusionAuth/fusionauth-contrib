package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;

/**
 * @author Dan Moore
 */
public class ExamplePBDKF2HMACSHA256Keylength512PasswordHasherTest {
  @Test(dataProvider = "hashes")
  public void encrypt(String password, String salt, String hash) {
    PasswordEncryptor encryptor = new ExamplePBDKF2HMACSHA256KeyLength512PasswordHasher();
    assertEquals(encryptor.encrypt(password, salt, 27500), hash);
  }

  @DataProvider(name = "hashes")
  public Object[][] hashes() {
    return new Object[][]{
        {"password", "jVqbuA9k2Mlo37OWXBMKLw==", "LjFqvhPuUHJdQvWIwVQfqxjeujAWqG/DVQRFoOv62/cTznl9ob4jwWwY6i1RrwGviu5iNPU5VIp03SxDyetyfw=="},
        {"password", "XnYZtiSCmFE5HF/ZplNCow==", "OFb32+cOm0sgtEcmNiGC83A27Sk1gb0fynv0RUXBfFXEJ/EieUPlx4uDAGA/q0/FkX2iHO45qqoIqBesiYMIUg=="},
    };
  }
}
