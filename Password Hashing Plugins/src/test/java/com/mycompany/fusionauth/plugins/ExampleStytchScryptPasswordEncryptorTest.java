package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;
import java.io.Console;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ritza
 */
public class ExampleStytchScryptPasswordEncryptorTest {

  @Test
  public void encrypt() {
    var encryptor = new ExampleStytchScryptPasswordEncryptor();

    var hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n1", "BbV-sGQqUIX1NwE6uqtSITv4fa1iMw==");
    Assert.assertEquals(calculated_hash, "uiOC_BwbKta9R9QL6Ss6KTDpCcULh9_zhObl5j4398M=");

    hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n2", "zKia-0BdIFKCzWbzXbj3qrhBnbiWNg==");
    Assert.assertEquals(calculated_hash, "8dg6AaIWPfcLTQU7lb4H-CI49dHeqaBXfFE1ogb2qRQ=");

    hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n3", "XPapDAm6xdV5UhMpyPrSpy8FbfCDtA==");
    Assert.assertEquals(calculated_hash, "A6VzdsTsTHPqafORIb0GkGD6qFxdncqwA15YXRsVgvs=");
  }

}