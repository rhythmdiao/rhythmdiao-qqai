package com.github.rhythmdiao.qqai.util;


import java.io.IOException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

/**
 * @author rhyth
 */
public class QQAIUtil {
    /**
     * 计算sign值
     *
     * @param params 参数组
     * @param appKey app_key
     * @return sign
     * @throws IOException
     */
    public static String getSign(Map<String, String> params, String appKey) throws IOException {
        Map<String, String> map = new TreeMap<>(params);
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            stringBuilder.append(entry.getKey()).append("=").append(URLEncoder.encode(entry.getValue(), "UTF-8")).append("&");
        }
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1).append("&app_key=").append(appKey);
        }
        try {
            return getMD5(stringBuilder.toString());
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    /**
     * 计算MD5摘要指值
     *
     * @param text 待编码字符串
     * @return String
     */
    private static String getMD5(String text) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            byte[] btInput = text.getBytes();
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (byte byte0 : md) {
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getNonceUtil() {
        String result = UUID.randomUUID().toString();
        result = result.replaceAll("-", "");
        return result;
    }

    public static String getNonceUtilFor10() {
        String result = UUID.randomUUID().toString();
        result = result.replaceAll("-", "").substring(0, 10);
        return result;
    }
}
