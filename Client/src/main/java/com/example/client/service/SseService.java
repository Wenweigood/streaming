package com.example.client.service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

import org.springframework.stereotype.Service;

/**
 * sse客户端服务
 */
@Service
public class SseService {

    /**
     * <p>测试方法{@link com.example.client.ClientApplicationTests#sendTest()}</p>
     * @param uri 请求地址
     * @throws IOException
     * @throws InterruptedException
     */
    public void receiveSse(String uri) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uri))
                .header("Accept", "text/event-stream")
                .timeout(Duration.ofSeconds(60))
                .build();

        HttpResponse<InputStream> response = client.send(request, HttpResponse.BodyHandlers.ofInputStream());

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(response.body()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // SSE 消息通常以行结束，这里你可以解析每一行
                // 注意：SSE 的每一行可能是一个空行（消息结束）、注释或数据行
                if (!line.trim().isEmpty() && line.startsWith("data:")) {
                    // 假设这是数据行（以 "data:" 开头），但这里我们没有检查前缀
                    // 你应该添加适当的逻辑来解析 SSE 消息
                    System.out.println("Received SSE message: " + line);
                }
            }
        }
    }
}
