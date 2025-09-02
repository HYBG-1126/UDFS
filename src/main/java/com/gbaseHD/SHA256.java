package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.Text;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * SHA256 UDF for Hive
 * 计算输入字符串的SHA-256哈希值
 */
@Description(
        name = "sha256",
        value = "_FUNC_(str) - 返回输入字符串的SHA-256哈希值",
        extended = "示例：\n" +
                "  > SELECT _FUNC_('test');\n" +
                "  '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08'\n"
)
public class SHA256 extends UDF {

    /**
     * 计算输入字符串的SHA-256哈希值
     *
     * @param input 输入字符串
     * @return SHA-256哈希值，如果输入为null则返回null
     */
    public Text evaluate(Text input) {
        if (input == null) {
            return null;
        }
        return new Text(sha256(input.toString()));
    }

    /**
     * 计算输入二进制数据的SHA-256哈希值
     *
     * @param input 输入二进制数据
     * @return SHA-256哈希值，如果输入为null则返回null
     */
    public Text evaluate(BytesWritable input) {
        if (input == null) {
            return null;
        }
        return new Text(sha256(input.getBytes(), 0, input.getLength()));
    }

    /**
     * 计算字符串的SHA-256哈希值
     *
     * @param input 输入字符串
     * @return SHA-256哈希值的十六进制字符串表示
     */
    private String sha256(String input) {
        return sha256(input.getBytes(StandardCharsets.UTF_8), 0, input.getBytes(StandardCharsets.UTF_8).length);
    }

    /**
     * 计算二进制数据的SHA-256哈希值
     *
     * @param bytes  输入二进制数据
     * @param offset 起始偏移量
     * @param length 长度
     * @return SHA-256哈希值的十六进制字符串表示
     */
    private String sha256(byte[] bytes, int offset, int length) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            digest.update(bytes, offset, length);
            return bytesToHex(digest.digest());
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256算法不可用", e);
        }
    }

    /**
     * 将字节数组转换为十六进制字符串
     *
     * @param bytes 字节数组
     * @return 十六进制字符串
     */
    private String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}