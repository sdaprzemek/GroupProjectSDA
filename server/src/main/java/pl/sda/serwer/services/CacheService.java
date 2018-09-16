package pl.sda.serwer.services;

import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheService {

    public static Map<String, String> cacheMap = Collections.synchronizedMap(new WeakHashMap<String, String>());

    public static void add(String requestPath, String responseAsJsonString){
            cacheMap.putIfAbsent(requestPath, responseAsJsonString);
    }

    public static void replace(String requestPath, String responseAsJsonString){
        if(cacheMap.containsKey(requestPath)){
            cacheMap.put(requestPath, responseAsJsonString);
        }
    }

    public static String get(String requestPath){
        return cacheMap.get(requestPath);
    }

    public static boolean isCached(String requestPath){
        return cacheMap.containsKey(requestPath);
    }
}
