package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import java.util.Base64;

/**
 * 3DES算法解密
 * 输入密文buff以及加密密钥key（168位），输出明文
 */
public class _023_TripleDESDecrypt extends UDF {
    
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
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // 执行解密操作
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(value));
            
            // 返回解密结果
            return new String(decryptedData, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("3DES decryption error: " + e.getMessage(), e);
        }
    }
}