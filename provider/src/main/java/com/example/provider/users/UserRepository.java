package com.example.provider.users;

import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    public User findById(String id) {
        if (id.equals("0")) {
            return new User("Max", List.of("Admin", "User"));
        }
        return null;
    }
}
