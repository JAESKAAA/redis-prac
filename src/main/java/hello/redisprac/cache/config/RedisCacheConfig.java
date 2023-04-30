package hello.redisprac.cache.config;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.CacheKeyPrefix;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisCacheConfig {

    @Bean
    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration configuration = RedisCacheConfiguration.defaultCacheConfig()
            .disableCachingNullValues()
            .entryTtl(Duration.ofSeconds(10)) //기본 TTL
            .computePrefixWith(CacheKeyPrefix.simple())
            .serializeKeysWith(SerializationPair.fromSerializer(
                new StringRedisSerializer()));

        /**
         * 특정 캐시에 대해서만 따로 설정하여 config에 포함시킬 수 있음
         */
        Map<String, RedisCacheConfiguration> configMap = new HashMap<>();
        configMap.put("userAgeCache",
            RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(5))); //특정 캐시에 대해서만 TTL 5초로 지정

        return RedisCacheManager.RedisCacheManagerBuilder
            .fromConnectionFactory(connectionFactory)
            .cacheDefaults(configuration)
            .withInitialCacheConfigurations(configMap)
            .build();
    }

}
