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
import org.springframework.transaction.annotation.Transactional;

import static com.jayway.jsonpath.JsonPath.read;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = BackendApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@Transactional
public class AdminEndpointIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void adminCanAccessAdminEndpoint() throws Exception {
        String loginBody = """
                {
                  "username": "admin",
                  "password": "test-admin-password"
                }
                """;

        MvcResult mvcResult = mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginBody))
                .andExpect(status().isOk())
                .andReturn();

        String adminToken = read(mvcResult.getResponse().getContentAsString(), "$.jwt");

        mvc.perform(get("/admin/trips")
                        .header("Authorization", "Bearer " + adminToken))
                .andExpect(status().isOk());
    }


    @Test
    void userCanNotAccessAdminEndpoint() throws Exception {
        String registerBody = """
                {
                  "username": "user",
                  "email": "user@example.com",
                  "password": "secret123"
                }
                """;

        mvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(registerBody))
                .andExpect(status().isCreated());

        String loginBody = """
                {
                  "username": "user",
                  "password": "secret123"
                }
                """;

        MvcResult mvcResult = mvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(loginBody))
                .andExpect(status().isOk())
                .andReturn();

    String userToken = read(mvcResult.getResponse().getContentAsString(), "$.jwt");

    mvc.perform(get("/admin/trips")
                    .header("Authorization", "Bearer " + userToken))
            .andExpect(status().isForbidden());
    }



}



