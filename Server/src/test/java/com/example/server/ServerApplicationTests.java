package com.example.server;

import com.example.server.controller.SseController;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@SpringBootTest
class ServerApplicationTests {

    @Resource
    SseController sseController;

    @Test
    void contextLoads() {
    }

    @Test
    void name() throws InterruptedException {
        Thread thread = new Thread(()->{
            SseEmitter sseEmitter = sseController.streamSse();
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        thread.start();
        thread.join();
    }
}
