package io.stuart.utils;

import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import io.stuart.consts.CacheConst;

public class AesUtil {

    private static final AES aes;

    static {
        Digester md5 = new Digester(DigestAlgorithm.MD5);

        // md5 bytes
        byte[] raw = md5.digest(CacheConst.SYS_AES_KEY);
        // get key bytes
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue(), raw).getEncoded();

        aes = SecureUtil.aes(key);
    }

    public static String encryptBase64(String src) {
        return aes.encryptBase64(src);
    }

    public static String decryptStrFromBase64(String dest) {
        return aes.decryptStrFromBase64(dest);
    }

}
