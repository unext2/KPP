package com.example.demo;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WeekDayControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void ReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/weekday?year=2022&day=66",
                String.class)).contains("Day \" 66 \" of \"2022\" year is Monday");
    }
    @Test
    public void ReturnEmpty() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/weekday?year=&day=",
                String.class)).contains("Empty param sended");
    }
    @Test
    public void ReturnBadWord() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/weekday?yearr=&day=",
                String.class)).contains("Bad name");
    }
}
