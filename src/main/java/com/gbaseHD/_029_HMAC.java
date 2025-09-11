package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.UDF;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * HMAC UDF for Hive
 * 使用密钥对输入数据进行HMAC哈希处理
 */

public class _029_HMAC extends UDF {


    public String evaluate(String data, String key) {

        // 生成HMAC-SHA256哈希值
        return generateHMACSHA256(data.getBytes(StandardCharsets.UTF_8), key);
    }

    public String evaluate(byte[] data, String key) {

        // 生成HMAC-SHA256哈希值
        return generateHMACSHA256(data, key);
    }

    public static String generateHMACSHA256(byte[] data, String key) {
        try {
            // 创建一个SecretKeySpec对象，使用指定的密钥和算法
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            // 获取Mac实例，并初始化
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);
            // 计算哈希值
            byte[] hmacBytes = mac.doFinal(data);
            // 将哈希值转换为Base64编码的字符串
            return Base64.getEncoder().encodeToString(hmacBytes);
        } catch (Exception e) {
            throw new RuntimeException("生成HMAC-SHA256失败", e);
        }
    }
}