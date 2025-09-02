package com.gbaseHD;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * UUID UDF for Hive
 * 生成通用唯一标识符
 */
@Description(
        name = "uuid",
        value = "_FUNC_() - 返回一个UUID字符串",
        extended = "示例：\n" +
                "  > SELECT _FUNC_();\n" +
                "  '550e8400-e29b-41d4-a716-446655440000'\n"
)
public class UUID extends UDF {

    /**
     * 生成一个UUID字符串
     *
     * @return UUID字符串
     */
    public Text evaluate() {
        return new Text(java.util.UUID.randomUUID().toString());
    }

    /**
     * 生成指定数量的UUID字符串，以逗号分隔
     *
     * @param count UUID数量
     * @return 逗号分隔的UUID字符串
     */
    public Text evaluate(Integer count) {
        if (count == null || count <= 0) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if (i > 0) {
                sb.append(",");
            }
            sb.append(java.util.UUID.randomUUID().toString());
        }

        return new Text(sb.toString());
    }
}