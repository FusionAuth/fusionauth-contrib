package com.mycompany.fusionauth.plugins;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author Ritza
 */
public class ExampleStytchScryptPasswordEncryptorTest {

  ExampleStytchScryptPasswordEncryptor encryptor = new ExampleStytchScryptPasswordEncryptor();

  @Test
  public void encrypt1() {
    String hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n1", "BbV+sGQqUIX1NwE6uqtSITv4fa1iMw==", 0);
    Assert.assertEquals(hash, "uiOC/BwbKta9R9QL6Ss6KTDpCcULh9/zhObl5j4398M=");
  }

  @Test
  public void encrypt2() {
    String hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n2", "zKia+0BdIFKCzWbzXbj3qrhBnbiWNg==", 0);
    Assert.assertEquals(hash, "8dg6AaIWPfcLTQU7lb4H+CI49dHeqaBXfFE1ogb2qRQ=");
  }

  @Test
  public void encrypt3() {
    String hash = encryptor.encrypt("averylongandunguessablepasswordwithlotsofrandominfooofisjoafasnr;,n3", "XPapDAm6xdV5UhMpyPrSpy8FbfCDtA==", 0);
    Assert.assertEquals(hash, "A6VzdsTsTHPqafORIb0GkGD6qFxdncqwA15YXRsVgvs=");
  }

}