package com.gbaseHD;

import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.apache.calcite.avatica.org.apache.commons.codec.binary.StringUtils;
import org.apache.hadoop.hive.ql.exec.UDF;

public class _027_Hide extends UDF {

    public String evaluate(String data, String key) {
        if (data == null || "".equals(data)) {
            return null;
        }
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return  StringUtils.newStringUtf8(sm4.decrypt(data));
    }
}

