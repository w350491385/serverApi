package com.xw.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangdongbin on 2018/4/11.
 */
public class RequestContextUtils {

   private static ThreadLocal<Map> threadLocal = new ThreadLocal<Map>(){
        @Override
        protected HashMap<String,Object> initialValue() {
            return new HashMap<String,Object>();
        }
    };

    public static String getSessionId(){
        Map<String,Object> dataMap = threadLocal.get();
        String sessionId = (String)dataMap.get(com.googlecode.jsonrpc4j.Constant.SESSIONID);
        return sessionId;
    }
    public static void setSessionId(String sessionId){
        Map<String,Object> dataMap = threadLocal.get();
        if (dataMap == null)
            dataMap = new HashMap<>();
        dataMap.put(com.googlecode.jsonrpc4j.Constant.SESSIONID,sessionId);
    }

    public static Object get(String key){
        Map<String,Object> dataMap = threadLocal.get();
        return dataMap.get(key);
    }

    public static void set(String key,Object data){
        Map<String,Object> dataMap = threadLocal.get();
        if (dataMap == null)
            dataMap = new HashMap<>();
        dataMap.put(key,data);
    }
}
