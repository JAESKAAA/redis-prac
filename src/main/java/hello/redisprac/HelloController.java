package hello.redisprac;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final StringRedisTemplate redisTemplate;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/setFruit")
    public String setFruit(@RequestParam String name) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        ops.set("fruit" , name);

        return "saved.";

    }

    @GetMapping("/getFruit")
    public String getFruit(@RequestParam String name) {

        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String result = ops.get(name);

        return "result = " + result;

    }
}
