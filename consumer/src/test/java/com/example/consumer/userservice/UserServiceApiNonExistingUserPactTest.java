package com.example.consumer.userservice;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.util.HashMap;
import java.util.Map;

import au.com.dius.pact.consumer.MockServer;
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
class UserServiceApiNonExistingUserPactTest {

    @Autowired
    private UserServiceApi userServiceApi;

    @SuppressWarnings("unused")
    @Pact(provider = "UserProvider", consumer = "UserConsumer")
    public RequestResponsePact requestValidUser(PactDslWithProvider builder) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return builder
            .given("A user for the id 1 does not exist")
            .uponReceiving("UserServiceApiPactTest invalid user interaction")
            .path("/users/1")
            .method("GET")
            .willRespondWith()
            .headers(headers)
            .status(404)
            .toPact();
    }

    @Test
    public void givenInvalidRequestForTestUser_shouldThrowException(@SuppressWarnings("unused") MockServer mockServer) {
        assertThatExceptionOfType(Exception.class).isThrownBy(() -> userServiceApi.getUserById("1"));
    }
}
