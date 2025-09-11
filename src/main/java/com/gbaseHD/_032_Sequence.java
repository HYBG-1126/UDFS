package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.security.SecureRandom;

/**
 * Sequence UDF for Hive
 * 随机生成一个序列替换原整数类型的数据，该序列前两位是字符，后两位是数字
 */
@Description(
        name = "sequence",
        value = "_FUNC_(number) - 随机生成一个序列替换原整数类型的数据，该序列前两位是字符，后两位是数字",
        extended = "示例：\n" +
                "  > SELECT _FUNC_(23);\n" +
                "  'QY96'  -- 随机生成的序列，前两位是字符，后两位是数字\n"
)
public class _032_Sequence extends UDF {

    // 字母字符集（大写字母）
    private static final String ALPHA_CHARSET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    // 数字字符集
    private static final String DIGIT_CHARSET = "0123456789";
    // 安全随机数生成器
    private final SecureRandom random = new SecureRandom();

    /**
     * 随机生成一个序列替换原整数类型的数据，该序列前两位是字符，后两位是数字
     *
     * @param number 待替换的整型数字
     * @return 生成的序列，前两位是字符，后两位是数字
     */
    public String evaluate(Integer number) {
        // 处理空值情况
        if (number == null) {
            return null;
        }

        StringBuilder result = new StringBuilder(4);

        // 生成前两位随机字符
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(ALPHA_CHARSET.length());
            result.append(ALPHA_CHARSET.charAt(index));
        }

        // 生成后两位随机数字
        for (int i = 0; i < 2; i++) {
            int index = random.nextInt(DIGIT_CHARSET.length());
            result.append(DIGIT_CHARSET.charAt(index));
        }

        return result.toString();
    }

    /**
     * 重载方法，支持int类型输入
     *
     * @param number 待替换的整型数字
     * @return 生成的序列，前两位是字符，后两位是数字
     */
    public String evaluate(int number) {
        return evaluate(Integer.valueOf(number));
    }
}
