package com.avi.ubi.carpark.controller;

import static org.hamcrest.Matchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class ChargingPointControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void test_connect() throws Exception {
        mockMvc
            .perform(patch("/chargingPoints/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"occupied\": true}"))
            .andDo(print())
            .andExpect(status().isOk());

        mockMvc
            .perform(get("/chargingPoints"))
            .andDo(print())
            .andExpect(status().isOk())
            .andExpect(jsonPath("$[0].id", is(1)))
            .andExpect(jsonPath("$[0].occupied", is(true)))
            .andExpect(jsonPath("$[0].amps", is(20)))
            .andExpect(jsonPath("$[0].occupiedSince", is(any(String.class))));
    }
}