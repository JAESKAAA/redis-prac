package hello.redisprac.session.login;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    //in-memory storage로 가정. 분산 환경에서는 애플리케이션 별로 sessionID를 공유하지 못하는 문제가 생김
    Map<String, String> sessionMap = new HashMap<>();


    @GetMapping("/login")
    public String login(HttpSession session, @RequestParam String name) {
        sessionMap.put(session.getId(), name);
        return "saved.";
    }

    @GetMapping("/myName")
    public String myName(HttpSession session) {
        String name = sessionMap.get(session.getId());
        return name;
    }
}
