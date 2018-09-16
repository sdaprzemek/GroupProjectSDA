package pl.sda.serwer.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SchedulerService {

    private static final int MAX_CACHED_OBJECTS_NUMBER = 10000;
    private Map<String, String> cacheMap = CacheService.cacheMap;

    @Scheduled(cron = "${scheduler.cache.refresh.time}")
    public void shouldCacheMapBeCleared(){
        if(cacheMap.size() >= MAX_CACHED_OBJECTS_NUMBER){
            cacheMap.clear();
        }
    }
}
