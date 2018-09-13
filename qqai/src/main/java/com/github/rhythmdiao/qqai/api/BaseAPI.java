package com.github.rhythmdiao.qqai.api;

import com.github.rhythmdiao.qqai.util.QQAIUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rhyth
 */
public class BaseAPI {
    private String appID;
    private String appKey;

    public BaseAPI(String appID, String appKey) {
        this.appID = appID;
        this.appKey = appKey;
    }

    public Map<String, String> getBaseParamsNonceStr32() {
        Map<String, String> map = new HashMap<>();
        map.put("app_id", appID);
        map.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("nonce_str", QQAIUtil.getNonceUtil());
        return map;
    }

    public Map<String, String> getBaseParamsNonceStr10() {
        Map<String, String> map = new HashMap<>();
        map.put("app_id", appID);
        map.put("time_stamp", String.valueOf(System.currentTimeMillis() / 1000));
        map.put("nonce_str", QQAIUtil.getNonceUtilFor10());
        return map;
    }
}
