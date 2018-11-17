package com.toutiao.officedict.common.httpUtil;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.HmacAlgorithms;

import java.io.UnsupportedEncodingException;

/**
 * Created by 18710 on 2018/7/25.
 */
public class HmacUtils {
    public static String s(String data, String key) {

        byte[] bytes = new byte[0];
        try {
            bytes = org.apache.commons.codec.digest.HmacUtils.getInitializedMac(HmacAlgorithms.HMAC_SHA_1,key.getBytes("UTF-8")).doFinal(data.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return new Hex().encodeHexString(bytes);
    }
}
