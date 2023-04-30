package hello.redisprac.rank.service;

import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
class RankingServiceTest {

    @Autowired
    RankingService rankingService;

    @Test
    @DisplayName("redis에서 특정 랭크를 가져오는 시간을 측정한다.")
    void get_rank_test() {

        //커텍션 획득을 위한 시간을 제외하기 위해 미리 한번 호출해줌
        rankingService.getTopRank(1);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Long userRank = rankingService.getUserRanking("user_100");
        stopWatch.stop();

        /**
         * 유저 랭크 조회 시간 약 11ms
         */
        System.out.println(String.format("Rank(%d) - Took %d ms", userRank, stopWatch.getTotalTimeMillis()));

        stopWatch.start();
        List<String> userRanks = rankingService.getTopRank(10);
        stopWatch.stop();

        /**
         * 유저 랭크 순위 조회 시간 약 3ms
         *
         * range가 더 빠른 이유는, 오름/내림 차순기준으로 끝부분만 조회하면 되기 떄문          */
        System.out.println(String.format("Range - Took %d ms", stopWatch.getTotalTimeMillis()));
    }

    @Test
    @DisplayName("redis에 100만건 삽입 테스트")
    void insert_in_redis_test() {
            StopWatch stopWatch = new StopWatch();
            stopWatch.start();
        for (int i = 0; i < 1_000_000; i++) {

            int score = (int) (Math.random() * 1_000_000);
            String userId = "user_" + i;
            rankingService.setUserScore(userId, score);
        }
            stopWatch.stop();
        System.out.println("seconds = " + stopWatch.getTotalTimeSeconds());
    }
}