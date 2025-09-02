package com.gbaseHD;


import cn.hutool.crypto.symmetric.SM4;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import org.apache.hadoop.hive.ql.exec.UDF;
import java.nio.charset.StandardCharsets;

public class SM4Encode extends UDF {
    
    /**
     * SM4加密方法
     * @param data 待加密数据
     * @param key 加密密钥(必须16位)
     * @return 加密后的十六进制字符串
     */
    public String evaluate(String data, String key) {
        if (data == null || key == null) {
            return null;
        }
        
        // 检查密钥长度是否为16位
        if (key.length() != 16) {
            throw new IllegalArgumentException("SM4 key must be 16 characters");
        }
        
        SM4 sm4 = new SM4(Mode.ECB, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8));
        return sm4.encryptHex(data);
    }
}

