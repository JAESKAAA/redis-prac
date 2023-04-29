package hello.redisprac.cache.service;

import hello.redisprac.cache.dto.UserProfile;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    private final StringRedisTemplate redisTemplate;

    public UserProfile getUserProfile(String userId) {

        String username = null;

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String key = "nameKey" + userId;

        String cachedName = ops.get(key);

        if (cachedName != null) {
            username = cachedName;
        } else {
            username = externalApiService.getUserName(userId);
            ops.set(key, username, 5, TimeUnit.SECONDS);
        }

        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(username, userAge);
    }


}
