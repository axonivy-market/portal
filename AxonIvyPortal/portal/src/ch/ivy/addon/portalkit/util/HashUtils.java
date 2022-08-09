package ch.ivy.addon.portalkit.util;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class HashUtils {

  private static final String SHA_256 = "SHA-256";

  public static String hash(String value) throws NoSuchAlgorithmException {
    byte[] salt = new byte[16];
    SecureRandom random = new SecureRandom();
    random.nextBytes(salt);

    MessageDigest md = MessageDigest.getInstance(SHA_256);
    md.update(salt);
    byte[] hashedToken = md.digest(value.getBytes(StandardCharsets.UTF_8));

    // Convert byte array into positive BigInteger
    BigInteger number = new BigInteger(1, hashedToken);
    // Convert message digest into hex value
    StringBuilder hexString = new StringBuilder(number.toString(16));

    return hexString.toString();
  }
}
