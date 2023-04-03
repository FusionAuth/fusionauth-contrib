package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;

import java.io.Console;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Bradley Van Aardt
 */
public class ExampleASPIdentityV2PasswordEncryptorTest {
  @Test
  public void encrypt() {
    
    PasswordEncryptor encryptor = new ExampleASPIdentityV2PasswordEncryptor();
    String calculated_hash = encryptor.encrypt("Pa$$w0rd", "6kdZf5YTMc+TOesPH3Tgog==", 1000);
    Assert.assertEquals(calculated_hash, "5PIdsgm04ogWFXbJEzf9U6HhLqQJWPP80rB9981LIwY=");
  }
}
