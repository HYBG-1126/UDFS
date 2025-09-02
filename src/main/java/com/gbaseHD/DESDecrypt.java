package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.util.Base64;

/**
 * DES算法解密
 * 输入密文buff以及加密密钥key（56位），输出明文
 */
public class DESDecrypt extends UDF {
    
    public String evaluate(String buff, String key) {
        try {
            // 处理空值情况
            if (buff == null || key == null) {
                return null;
            }
            
            // 创建DES密钥规范
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));
            
            // 创建密钥工厂
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            
            // 生成密钥
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
            
            // 创建并初始化Cipher对象
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            
            // 执行解密操作
            byte[] decryptedData = cipher.doFinal(Base64.getDecoder().decode(buff));
            
            // 返回解密结果
            return new String(decryptedData, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException("DES decryption error: " + e.getMessage(), e);
        }
    }
}