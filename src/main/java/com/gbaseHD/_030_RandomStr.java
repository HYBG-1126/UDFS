package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

import java.security.SecureRandom;

/**
 * RandomStr UDF for Hive
 * 生成指定长度的随机字符串
 */
@Description(
        name = "randomstr",
        value = "_FUNC_(length[, charSet]) - 返回指定长度的随机字符串",
        extended = "示例：\n" +
                "  > SELECT _FUNC_(10);\n" +
                "  'a1b2c3d4e5'\n" +
                "  > SELECT _FUNC_(8, 'ABCDEF0123456789');\n" +
                "  'F12A3B4C'\n"
)
public class _030_RandomStr extends UDF {

    // 默认字符集：数字和字母
    private static final String DEFAULT_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    
    // 安全随机数生成器
    private final SecureRandom random = new SecureRandom();

    /**
     * 生成指定长度的随机字符串，使用默认字符集
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public Text evaluate(Integer length) {
        if (length == null || length <= 0) {
            return null;
        }
        return new Text(generateRandomString(length, DEFAULT_CHARSET));
    }

    /**
     * 生成指定长度的随机字符串，使用自定义字符集
     *
     * @param length  随机字符串长度
     * @param charSet 自定义字符集
     * @return 随机字符串
     */
    public Text evaluate(Integer length, String charSet) {
        if (length == null || length <= 0 || charSet == null || charSet.isEmpty()) {
            return null;
        }
        return new Text(generateRandomString(length, charSet));
    }

    /**
     * 生成指定长度的随机字符串
     *
     * @param length  随机字符串长度
     * @param charSet 字符集
     * @return 随机字符串
     */
    private String generateRandomString(int length, String charSet) {
        StringBuilder sb = new StringBuilder(length);
        int charSetLength = charSet.length();
        
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(charSetLength);
            sb.append(charSet.charAt(randomIndex));
        }
        
        return sb.toString();
    }
}