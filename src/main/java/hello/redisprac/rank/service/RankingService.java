package hello.redisprac.rank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final StringRedisTemplate redisTemplate;

    private static final  String LEADERBOARD_KEY = "leaderBoard";

    public boolean setUserScore(String userId, int score) {

        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();

        return zSetOps.add(LEADERBOARD_KEY, userId, score);
    }

    public Long getUserRanking(String userId) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        return zSetOps.reverseRank  (LEADERBOARD_KEY, userId);
    }

    public List<String> getTopRank(int limit) {
        ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
        Set<String> rankSet = zSetOps.reverseRange(LEADERBOARD_KEY, 0, limit - 1);
        return new ArrayList<>(rankSet);
    }
}
