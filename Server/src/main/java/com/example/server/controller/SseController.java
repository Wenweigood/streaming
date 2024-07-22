package com.example.server.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Controller
public class SseController {

    /**
     * 服务端接口
     * @return
     */
    @GetMapping(value = "/stream-sse", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter streamSse() {
        SseEmitter emitter = new SseEmitter();

        // 使用新的线程发送数据，避免阻塞主线程
        new Thread(() -> {
            try {
                for (int i = 0; i < 100; i++) {
                    // 发送带有事件名称和数据的SSE消息
                    emitter.send(SseEmitter.event().name("update").data("Message " + i));
                    System.out.println("sent: "+ i);
                    Thread.sleep(1000); // 模拟数据处理延迟
                }
                // 完成SSE流
                emitter.complete();
            } catch (Exception e) {
                emitter.completeWithError(e);
            }
        }).start();

        return emitter;
    }
}
