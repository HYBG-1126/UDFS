package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES算法解密
 * 输入二进制密文以及密钥，输出明文
 */
public class AESDecrypt extends UDF {
    
    public String evaluate(byte[] input, String key) {
        try {
            // 处理空值情况
            if (input == null || key == null) {
                return null;
            }
            
            // 创建AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            
            // 创建并初始化Cipher对象
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // 执行解密操作
            byte[] decryptedData = cipher.doFinal(input);
            
            // 返回解密结果
            return new String(decryptedData, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("AES decryption error: " + e.getMessage(), e);
        }
    }
    
    // 重载方法，支持Base64编码的字符串输入
    public String evaluate(String input, String key) {
        try {
            // 处理空值情况
            if (input == null || key == null) {
                return null;
            }
            
            // Base64解码
            byte[] encryptedBytes = Base64.getDecoder().decode(input);
            
            // 创建AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            
            // 创建并初始化Cipher对象
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // 执行解密操作
            byte[] decryptedData = cipher.doFinal(encryptedBytes);
            
            // 返回解密结果
            return new String(decryptedData, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("AES decryption error: " + e.getMessage(), e);
        }
    }
}