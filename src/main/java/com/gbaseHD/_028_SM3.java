package com.gbaseHD;

import cn.hutool.crypto.SmUtil;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.bouncycastle.jce.provider.BouncyCastleProvider;


import java.nio.charset.StandardCharsets;
import java.security.Security;

/**
 * SM3 UDF for Hive
 * 计算输入字符串的SM3哈希值
 * SM3是中国国家密码管理局发布的密码杂凑算法标准
 */

public class _028_SM3 extends UDF {

    static {
        Security.addProvider(new BouncyCastleProvider());
    }

    public String evaluate(byte[] plainString) {
        return SmUtil.sm3(new String(plainString, StandardCharsets.UTF_8));
    }

    public String evaluate(String plainString) {
        return SmUtil.sm3(plainString);
    }


}