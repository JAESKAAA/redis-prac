package hello.redisprac.cache.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ExternalApiService {

    public String getUserName(String userId) {

        //실제 외부 호출하는 로직 구성됐다고 가정

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (userId.equals("A")) {
            return "Adam";
        }

        if (userId.equals("B")) {
            return "Bob";
        }
        return "";

    }

    public int getUserAge(String userId) {

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Getting User Age from Other Service");

        if (userId.equals("A")) {
            return 30;
        }

        if (userId.equals("B")) {
            return 25;
        }
        return 0;

    }
}
