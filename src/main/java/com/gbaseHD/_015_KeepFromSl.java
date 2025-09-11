package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;

/**
 * KeepFromSl UDF for Hive
 * 保留字符串自x至y位，其余部分用指定长度的掩码字符替换
 */
@Description(
        name = "keep_from_sl",
        value = "_FUNC_(data, x, y, l[, a]) - 保留字符串自x至y位，其余部分用指定长度的掩码字符替换",
        extended = "示例：\n" +
                "  > SELECT _FUNC_('浙江省杭州市西湖区胡同口', 2, 3, 3);\n" +
                "  '***江省***'\n" +
                "  > SELECT _FUNC_('浙江省杭州市西湖区胡同口', 2, 3, 3, '#');\n" +
                "  '###江省###'\n"
)
public class _015_KeepFromSl extends UDF {

    /**
     * 保留字符串自x至y位，其余部分用指定长度的掩码字符替换
     *
     * @param data 输入字符串
     * @param x    起始位置（从1开始）
     * @param y    结束位置（从1开始）
     * @param l    掩码字符长度
     * @return 处理后的字符串
     */
    public String evaluate(String data, Integer x, Integer y, Integer l) {
        return evaluate(data, x, y, l, '*');
    }

    /**
     * 保留字符串自x至y位，其余部分用指定长度的掩码字符替换
     *
     * @param data 输入字符串
     * @param x    起始位置（从1开始）
     * @param y    结束位置（从1开始）
     * @param l    掩码字符长度
     * @param a    掩码字符
     * @return 处理后的字符串
     */
    public String evaluate(String data, Integer x, Integer y, Integer l, Character a) {
        if (data == null || x == null || y == null || l == null || a == null) {
            return null;
        }

        x -= 1;

        // 参数校验
        if (x < 0 || y < 0 || x > data.length() || y > data.length() || x >= y || l < 0) {
            return data;
        }

        // 生成掩码字符串
        StringBuilder mask = new StringBuilder();
        for (int i = 0; i < l; i++) {
            mask.append(a);
        }

        // 构建结果字符串
        StringBuilder result = new StringBuilder();

        // 前面部分用掩码替换
        result.append(mask);

        // 保留x到y位置的字符
        result.append(data.substring(x, y));

        // 后面部分用掩码替换
        result.append(mask);

        return result.toString();
    }
}
