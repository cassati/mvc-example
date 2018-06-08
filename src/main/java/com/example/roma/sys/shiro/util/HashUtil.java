package com.example.roma.sys.shiro.util;

import com.example.framework.core.springside.SpringContextHolder;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.SimpleHash;

/**
 * 加密工具
 * @author zhengkw
 * @create_date 2018-06-08 10:36
 */
public class HashUtil {

    public static String hashMd5SaltedWithIterations(String source, String salt) {
        Object bean = SpringContextHolder.getBean("credentialsMatcher");
        if (bean == null) {
            throw new RuntimeException("Can not find the bean 'credentialsMatcher'.");
        }

        HashedCredentialsMatcher hcm = (HashedCredentialsMatcher) bean;
        if (!"md5".equalsIgnoreCase(hcm.getHashAlgorithmName())) {
            throw new RuntimeException("The hash algorithm is not md5.");
        }

        SimpleHash hash = new SimpleHash(hcm.getHashAlgorithmName(), source, salt, hcm.getHashIterations());
        if (hcm.isStoredCredentialsHexEncoded()) {
            return hash.toHex();
        }
        return hash.toBase64();
    }
}
