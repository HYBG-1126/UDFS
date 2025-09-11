package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.security.SecureRandom;

/**
 * Blur UDF for Hive
 * 随机生成一个double类型的数字替换原double类型数字
 */
@Description(
        name = "blur",
        value = "_FUNC_(data) - 随机生成一个double类型的数字替换原double类型数字",
        extended = "示例：\n" +
                "  > SELECT _FUNC_(234.0);\n" +
                "  199.17  -- 随机生成的double类型数字\n"
)
public class _033_Blur extends UDF {

    // 安全随机数生成器
    private final SecureRandom random = new SecureRandom();

    // 随机数范围的下限和上限（默认为原值的70%到130%）
    private static final double MIN_FACTOR = 0.7;
    private static final double MAX_FACTOR = 1.3;

    /**
     * 随机生成一个double类型的数字替换原double类型数字
     *
     * @param data 待替换的double类型数据
     * @return 随机生成的double类型数字
     */
    public Double evaluate(Double data) {
        // 处理空值情况
        if (data == null) {
            return null;
        }

        //处理0的时候
        if (data == 0) {
            data = 1.0;
        }

        // 计算随机范围
        double min = data * MIN_FACTOR;
        double max = data * MAX_FACTOR;

        // 生成随机数
        double randomValue = min + (random.nextDouble() * (max - min));

        // 保留两位小数
        return Math.round(randomValue * 100) / 100.0;
    }

    /**
     * 重载方法，支持double原始类型输入
     *
     * @param data 待替换的double类型数据
     * @return 随机生成的double类型数字
     */
    public Double evaluate(double data) {
        return evaluate(Double.valueOf(data));
    }
}
