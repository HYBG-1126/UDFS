package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

import java.security.SecureRandom;

/**
 * Random UDF for Hive
 * 生成指定范围内的随机整数
 */
@Description(
        name = "random",
        value = "_FUNC_([min, max]) - 返回指定范围内的随机整数",
        extended = "示例：\n" +
                "  > SELECT _FUNC_();\n" +
                "  0.7231942318  -- 返回0到1之间的随机浮点数\n" +
                "  > SELECT _FUNC_(100);\n" +
                "  42  -- 返回0到100之间的随机整数\n" +
                "  > SELECT _FUNC_(10, 20);\n" +
                "  15  -- 返回10到20之间的随机整数\n"
)
public class Random extends UDF {

    // 安全随机数生成器
    private final SecureRandom random = new SecureRandom();

    /**
     * 生成0到1之间的随机浮点数
     *
     * @return 0到1之间的随机浮点数
     */
    public Double evaluate() {
        return random.nextDouble();
    }

    /**
     * 生成0到max之间的随机整数（包含0，不包含max）
     *
     * @param max 最大值（不包含）
     * @return 随机整数
     */
    public Integer evaluate(Integer max) {
        if (max == null || max <= 0) {
            return null;
        }
        return random.nextInt(max);
    }

    /**
     * 生成min到max之间的随机整数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机整数
     */
    public Integer evaluate(Integer min, Integer max) {
        if (min == null || max == null || min >= max) {
            return null;
        }
        return min + random.nextInt(max - min);
    }

    /**
     * 生成min到max之间的随机长整数（包含min，不包含max）
     *
     * @param min 最小值（包含）
     * @param max 最大值（不包含）
     * @return 随机长整数
     */
    public Long evaluate(Long min, Long max) {
        if (min == null || max == null || min >= max) {
            return null;
        }
        return min + (long) (random.nextDouble() * (max - min));
    }
}