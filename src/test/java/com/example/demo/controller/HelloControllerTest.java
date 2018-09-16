package com.example.demo.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.HttpSession;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloControllerTest {
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    private MockHttpSession session;

    @Before
    public void setUp() throws Exception {
        session = new MockHttpSession();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void helloGet() throws Exception {
        MvcResult result = this.mockMvc.perform(
                get("/hello").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "Hello World!");
    }

    @Test
    public void helloPost() throws Exception {
        MvcResult result = this.mockMvc.perform(
                post("/hello").accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), "Hello World!");
    }

    @Test
    public void getName() throws Exception {
        this.mockMvc.perform(
                post("/hello").accept(MediaType.APPLICATION_JSON).session(session)
        )
                .andExpect(status().isOk())
                .andReturn();
        MvcResult result = this.mockMvc.perform(
                post("/getName").accept(MediaType.APPLICATION_JSON).session(session)
        )
                .andExpect(status().isOk())
                .andReturn();
        HttpSession requestSession = result.getRequest().getSession();
        assertEquals(requestSession.getAttribute("name"), "JIE");
    }

}