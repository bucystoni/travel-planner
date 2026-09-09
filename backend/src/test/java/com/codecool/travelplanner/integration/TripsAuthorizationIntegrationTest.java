package com.codecool.travelplanner.integration;

import com.codecool.travelplanner.BackendApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static com.jayway.jsonpath.JsonPath.read;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application.properties")
class TripsAuthorizationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void tripsEndpointRejectsRequestsWithoutToken() throws Exception {
        mvc.perform(get("/trips"))
                .andExpect(status().isForbidden());
    }

    @Test
    void tripsEndpointAcceptsRequestsWithValidToken() throws Exception {
        String registerBody = """
                {
                  "username": "dave",
                  "email": "dave@example.com",
                  "password": "secret123"
                }
                """;

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerBody))
                .andExpect(status().isCreated());

        String loginBody = """
                {
                  "username": "dave",
                  "password": "secret123"
                }
                """;

        MvcResult loginResult = mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginBody))
                .andExpect(status().isOk())
                .andReturn();

        String responseBody = loginResult.getResponse().getContentAsString();
        String token = read(responseBody, "$.jwt");

        mvc.perform(get("/trips")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk());
    }
}