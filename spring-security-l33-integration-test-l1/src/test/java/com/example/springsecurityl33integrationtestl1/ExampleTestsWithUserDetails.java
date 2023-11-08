package com.example.springsecurityl33integrationtestl1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ExampleTestsWithUserDetails {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Call endpoint with user from UserDetailsService")
    @WithUserDetails(value = "bill")
    void testAuthenticatedEndpointWithRealUserDetailsService() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk());
    }
}
