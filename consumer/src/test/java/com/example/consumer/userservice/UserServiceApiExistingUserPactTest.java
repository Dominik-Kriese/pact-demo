package com.example.consumer.userservice;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import au.com.dius.pact.consumer.MockServer;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "UserProvider", port = "8080")
@SpringBootTest
class UserServiceApiExistingUserPactTest {

    private final User testUser = new User("Max", List.of("Admin", "User"));

    @Autowired
    private UserServiceApi userServiceApi;

    @SuppressWarnings("unused")
    @Pact(provider = "UserProvider", consumer = "UserConsumer")
    public RequestResponsePact requestValidUser(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return builder
            .given("A user for the id 0 exists with name Max as Admin and User")
            .uponReceiving("UserServiceApiPactTest valid user interaction")
            .path("/users/0")
            .method("GET")
            .willRespondWith()
            .headers(headers)
            .status(200)
            .body(new PactDslJsonBody()
                .stringValue("name", "Max")
                .array("permissions")
                .stringValue("Admin")
                .stringValue("User")
                .closeArray())
            .toPact();
    }

    @Test
    public void givenValidRequestForTestUser_shouldReturnUserWithMatchingNameAndPermissions(MockServer mockServer) {
        var responseUser = userServiceApi.getUserById("0");
        assertThat(responseUser.getName()).isEqualTo(testUser.getName());
        assertThat(responseUser.getPermissions()).containsAll(testUser.getPermissions());
    }
}
