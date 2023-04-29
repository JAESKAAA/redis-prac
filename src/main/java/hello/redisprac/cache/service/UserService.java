package hello.redisprac.cache.service;

import hello.redisprac.cache.dto.UserProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ExternalApiService externalApiService;

    public UserProfile getUserProfile(String userId) {
        String username = externalApiService.getUserName(userId);
        int userAge = externalApiService.getUserAge(userId);

        return new UserProfile(username, userAge);
    }


}
