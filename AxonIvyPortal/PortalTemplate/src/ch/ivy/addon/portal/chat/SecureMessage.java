package ch.ivy.addon.portal.chat;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

public final class SecureMessage {

  private static final String ALGORITHM = "AES";
  private static final String UTF_8 = "UTF-8";

  private SecureMessage() {}

  public static String encrypt(String dataToEncrypt, String key) {
    Key secretKey = getSecretKey(key);
    Cipher cipher = getCipherInstance();
    try {
      cipher.init(Cipher.ENCRYPT_MODE, secretKey);
      byte[] encrypted = cipher.doFinal(dataToEncrypt.getBytes(UTF_8));
      return Base64.getEncoder().encodeToString(encrypted);
    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
      throw new PortalChatException("The give data was not encrypted successfully.", e);
    }
  }

  public static String decrypt(String dataToDecrypt, String key) {
    Key secretKey = getSecretKey(key);
    Cipher cipher = getCipherInstance();

    try {
      cipher.init(Cipher.DECRYPT_MODE, secretKey);
      byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(dataToDecrypt.getBytes(UTF_8)));
      return new String(decrypted, UTF_8);
    } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
      throw new PortalChatException("The give data was not decrypted successfully.", e);
    }
  }

  private static Cipher getCipherInstance() {
    try {
      return Cipher.getInstance(ALGORITHM);
    } catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
      throw new PortalChatException("Could not get cipher instance.", e);
    }
  }

  private static SecretKeySpec getSecretKey(String key) {
    try {
      return new SecretKeySpec(key.getBytes(UTF_8), 0, 16, ALGORITHM);
    } catch (UnsupportedEncodingException e) {
      throw new PortalChatException("Could not get secrect key.", e);
    }
  }
}
