package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

/**
 * 3DES算法加密
 * 输入待加密的data以及加密密钥key（168位），输出加密后的字符串（经过Base64编码）
 */
public class _022_TripleDESEncrypt extends UDF {
    
    public String evaluate(String value, String key) {
        try {
            // 处理空值情况
            if (value == null || key == null) {
                return null;
            }
            
            // 创建3DES密钥规范
            DESedeKeySpec desKeySpec = new DESedeKeySpec(key.getBytes("UTF-8"));
            
            // 创建密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
            
            // 生成密钥
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            
            // 创建并初始化Cipher对象
            Cipher cipher = Cipher.getInstance("DESede/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            
            // 执行加密操作
            byte[] encryptedData = cipher.doFinal(value.getBytes("UTF-8"));
            
            // 使用Base64编码返回加密结果
            return Base64.getEncoder().encodeToString(encryptedData);
        } catch (Exception e) {
            throw new RuntimeException("3DES encryption error: " + e.getMessage(), e);
        }
    }
}