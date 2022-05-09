package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;

import java.io.Console;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ritza
 */
public class ExampleFirebaseScryptPasswordEncryptorTest {
  @Test
  public void encrypt() {
    
    PasswordEncryptor encryptor = new ExampleFirebaseScryptPasswordEncryptor();
    String calculated_hash = encryptor.encrypt("fusionauth123", "KK9U4hSXPLgBSw==", 8);
    Assert.assertEquals(calculated_hash, "2Z/xOyVm3l0i4k9WNgMubOXKG6yBYj6U/wiSlvTK6w3Cgx47a4BvGAZUKFTubO43nheb8teCp0vDoIH6KPWyXQ==");
  }
}
