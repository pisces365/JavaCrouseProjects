package CTF1;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class aes {
  public static byte[] encrypt(String content, String password) {
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      byte[] byteContent = content.getBytes("utf-8");
      cipher.init(1, key);
      byte[] result = cipher.doFinal(byteContent);
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public static byte[] decrypt(byte[] content, String password) {
    try {
      KeyGenerator kgen = KeyGenerator.getInstance("AES");
      kgen.init(128, new SecureRandom(password.getBytes()));
      SecretKey secretKey = kgen.generateKey();
      byte[] enCodeFormat = secretKey.getEncoded();
      SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
      Cipher cipher = Cipher.getInstance("AES");
      cipher.init(2, key);
      byte[] result = cipher.doFinal(content);
      return result;
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    } catch (NoSuchPaddingException e) {
      e.printStackTrace();
    } catch (InvalidKeyException e) {
      e.printStackTrace();
    } catch (IllegalBlockSizeException e) {
      e.printStackTrace();
    } catch (BadPaddingException e) {
      e.printStackTrace();
    } 
    return null;
  }
  
  public static void main(String[] args) {
    String content = "input your flag";
    String password = "12345678";
    byte[] aaa = encrypt(content, password);
    byte[] encryptResult = { 
        -95, -100, 20, -126, -123, -51, -1, 46, -74, -108, 
        -27, -125, -34, -80, 83, 35, -55, -72, -21, 84, 
        -21, 72, 3, -43, -45, 61, 110, -92, -86, -7, 
        9, -24, -45, -105, 32, -15, 42, -89, -50, -71, 
        23, 21, 80, 59, -38, -9, -99, 97 };
    
    aaa = decrypt(encryptResult,password);
    
    for (int i = 0; i < encryptResult.length; i++) {
      if (aaa[i] != encryptResult[i]) {
        System.out.println("fail");
        return;
      } 
    } 
    System.out.println("success");
  }
}

