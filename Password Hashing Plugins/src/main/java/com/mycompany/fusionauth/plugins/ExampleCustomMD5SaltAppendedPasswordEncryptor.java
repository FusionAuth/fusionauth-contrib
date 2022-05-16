package com.mycompany.fusionauth.plugins;

import io.fusionauth.plugin.spi.security.PasswordEncryptor;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * This is an example of a MD5 Salted hashing algorithm with the salt appended.
 *
 * <pre>{@code
 * hash = md5(password + salt) + salt
 * }</pre>
 *
 * <p>
 * This code is provided to assist in your deployment and management of FusionAuth. Use of this
 * software is not covered under the FusionAuth license agreement and is provided "as is" without
 * warranty. https://fusionauth.io/license
 * </p>
 *
 * @author Joshua OBannon
 */
public class ExampleCustomMD5SaltAppendedPasswordEncryptor implements PasswordEncryptor {
    @Override
    public int defaultFactor() {
        return 1;
    }

    @Override
    public String encrypt(String password, String salt, int factor) {
        if (factor <= 0) {
            throw new IllegalArgumentException("Invalid factor value [" + factor + "]");
        }

        // Instantiate a messageDigest, and make it of type MD5.  This object will be used to do the actual hashing (messageDigest.digest(digest)) below
        MessageDigest messageDigest;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("No such algorithm [MD5]");
        }

        // Decode the base64 encoded salt passed into this `encrypt` method.
        byte[] decodedSalt = Base64.getDecoder().decode(salt.getBytes(StandardCharsets.UTF_8));

        // Join the password and salt
        byte[] digest = join(password.getBytes(StandardCharsets.UTF_8), decodedSalt);

        // Hash the digest (password and salt) based on the factor provided
        for (int i = 0; i < factor; i++) {
            digest = messageDigest.digest(digest);
        }

        // Join ( digest[pw + salt] ) + ( salt )
        byte[] copy = join(digest, decodedSalt);

        // Now we encode it (base64) and return it.
        return new String(Base64.getEncoder().encode(copy));
    }

    public byte[] join(byte[] first, byte[] second) {
        byte[] joined = new byte[first.length + second.length];
        System.arraycopy(first, 0, joined, 0, first.length);
        System.arraycopy(second, 0, joined, first.length, second.length);
        return joined;
    }
}
