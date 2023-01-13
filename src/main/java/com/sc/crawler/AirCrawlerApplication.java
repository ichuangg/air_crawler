package com.sc.crawler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sc.crawler.ws.ZQWebSocket;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.websocket.Session;
import java.io.IOException;

@SpringBootApplication
public class AirCrawlerApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(AirCrawlerApplication.class, args);
    }



}
