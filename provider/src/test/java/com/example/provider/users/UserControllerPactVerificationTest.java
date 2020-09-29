package com.example.provider.users;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit5.PactVerificationContext;
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@Provider("UserProvider")
@PactFolder("pacts")
class UserControllerPactVerificationTest {

    @MockBean
    private UserRepository userRepository;

    @State("A user for the id 0 exists with name Max as Admin and User")
    public void existingUserState() {
        when(userRepository.findById(any())).thenReturn(new User("Max", List.of("Admin", "User")));
    }

    @State("A user for the id 1 does not exist")
    public void nonExistentUserState() {
        when(userRepository.findById(any())).thenReturn(null);
    }

    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider.class)
    void pactVerificationTestTemplate(PactVerificationContext context) {
        context.verifyInteraction();
    }
}
