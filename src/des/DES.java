/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package des;

import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nhoclahola
 */
public class DES
{
    public static SecretKey generateSecretKey(String keyString) 
    {
        // Chuyển đổi chuỗi thành mảng byte
        byte[] keyBytes = keyString.getBytes();

        // Đảm bảo khóa có độ dài 8 byte bằng cách sử dụng mảng byte đầu tiên
        byte[] truncatedKey = new byte[8];
        System.arraycopy(keyBytes, 0, truncatedKey, 0, Math.min(keyBytes.length, truncatedKey.length));

        // Tạo secret key từ mảng byte
        return new SecretKeySpec(truncatedKey, "DES");
    }
    
        public static String encrypt(String plaintext, SecretKey secretKey, Cipher cipher) throws Exception 
    {
        // Thiết lập Cipher cho chế độ mã hoá và sử dụng khóa bí mật
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        // Mã hoá dữ liệu và chuyển đổi thành Base64 để dễ đọc
        byte[] encryptedBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
//        return new String(encryptedBytes);    //Dạng byte
    }

    public static String decrypt(String encryptedText, SecretKey secretKey, Cipher cipher) throws Exception 
    {
        // Thiết lập Cipher cho chế độ giải mã và sử dụng khóa bí mật
        cipher.init(Cipher.DECRYPT_MODE, secretKey);

        // Giải mã dữ liệu từ Base64
        byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);
        byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

        // Chuyển đổi dữ liệu giải mã thành chuỗi
        return new String(decryptedBytes);
    }
}
