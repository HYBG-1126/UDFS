package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * AES算法加密
 * 输入字符串/二进制待加密数据以及密钥，输出aes加密后的字符串
 */
public class AESEncrypt extends UDF {
    
    public String evaluate(String input, String key) {
        try {
            // 处理空值情况
            if (input == null || key == null) {
                return null;
            }
            
            // 创建AES密钥
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
            
            // 创建并初始化Cipher对象
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            // 执行加密操作
            byte[] encryptedData = cipher.doFinal(input.getBytes("UTF-8"));
            
            // 使用Base64编码返回加密结果
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("AES encryption error: " + e.getMessage(), e);
        }
    }
    
    // 重载方法，支持二进制输入
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
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            // 执行加密操作
            byte[] encryptedData = cipher.doFinal(input);
            
            // 使用Base64编码返回加密结果
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("AES encryption error: " + e.getMessage(), e);
        }
    }
}