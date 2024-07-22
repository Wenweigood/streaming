package com.example.client;

import com.example.client.service.SseService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ClientApplicationTests {

    @Resource
    SseService sseService;

    @Test
    void contextLoads() {
    }

    @Test
    void sendTest() throws IOException, InterruptedException {
        sseService.receiveSse("http://localhost:8080/stream-sse");
    }
}
