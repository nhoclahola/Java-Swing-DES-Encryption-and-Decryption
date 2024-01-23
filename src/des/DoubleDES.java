package des;


import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author nhoclahola
 */
public class DoubleDES
{
      public static SecretKey generateSecretKey(String key) throws Exception 
      {
        // Chuyển đổi chuỗi khóa thành mảng byte
        byte[] keyBytes = key.getBytes();

        // Tạo DESKeySpec từ mảng byte
        DESKeySpec desKeySpec = new DESKeySpec(keyBytes);

        // Sử dụng SecretKeyFactory để tạo SecretKey từ DESKeySpec
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        return keyFactory.generateSecret(desKeySpec);
    }

    public static String encrypt(String plaintext, SecretKey key1, SecretKey key2, Cipher cipher) throws Exception 
    {
        // Thiết lập Cipher cho chế độ mã hoá và sử dụng khóa bí mật
        cipher.init(Cipher.ENCRYPT_MODE, key1);

        // Mã hoá với khóa thứ nhất
        byte[] intermediate = cipher.doFinal(plaintext.getBytes());

        // Thiết lập Cipher cho chế độ mã hoá và sử dụng khóa bí mật thứ hai
        cipher.init(Cipher.ENCRYPT_MODE, key2);

        // Mã hoá với khóa thứ hai
        byte[] encryptedBytes = cipher.doFinal(intermediate);

        // Chuyển đổi thành Base64 để dễ đọc
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedText, SecretKey key1, SecretKey key2, Cipher cipher) throws Exception 
    {
        // Giải mã với khóa thứ hai
        cipher.init(Cipher.DECRYPT_MODE, key2);
        byte[] intermediate = cipher.doFinal(Base64.getDecoder().decode(encryptedText));

        // Giải mã với khóa thứ nhất
        cipher.init(Cipher.DECRYPT_MODE, key1);
        byte[] decryptedBytes = cipher.doFinal(intermediate);

        // Chuyển đổi dữ liệu giải mã thành chuỗi
        return new String(decryptedBytes);
    }
}
