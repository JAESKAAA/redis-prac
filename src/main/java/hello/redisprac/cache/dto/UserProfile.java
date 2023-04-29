package hello.redisprac.cache.dto;

import lombok.Getter;

@Getter
public class UserProfile {

    private String name;

    private int age;

    public UserProfile(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
