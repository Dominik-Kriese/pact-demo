package com.example.consumer.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;

@Component
public class UserServiceApi {

    private final RestTemplateBuilder restTemplateBuilder;

    @Autowired
    public UserServiceApi(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    public User getUserById(String userId) {
        return this.restTemplateBuilder.build()
            .getForObject("http://localhost:8080/users/" + userId, User.class);
    }

}
