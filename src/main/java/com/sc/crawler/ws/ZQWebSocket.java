package com.sc.crawler.ws;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;

import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Vector;

@ServerEndpoint(value = "/ws/zq")
@Controller
@Slf4j
public class ZQWebSocket {

    //param : {"city":"德宏州","type":"HOUR","startTime":"2022-11-14 12:00:36","endTime":"2022-11-15 15:21:36"} method :
    //    CETCITYPERIOD

    public static List<Session> sessions = new Vector<>();

    static ObjectMapper objectMapper = new ObjectMapper();
    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static boolean flag = false;
    static FileWriter fileWriter;

    static {
        try {
            fileWriter = new FileWriter("./completeTheCity.txt",true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @OnOpen
    public void onOpen(Session session) throws IOException, InterruptedException {
        sessions.add(session);
        log.info("连接成功、、");
        LocalDateTime start = LocalDateTime.parse("2014-01-01 00:00:00", dateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse("2014-01-31 23:00:00", dateTimeFormatter);
//        LocalDateTime end = LocalDateTime.parse("2014-12-31 23:00:00", dateTimeFormatter);
        List<String> citys = objectMapper.readValue(new File("./city.json"), List.class);
        for (String city : citys) {
            while (end.getYear() != 2022 || end.getDayOfYear() <= 340) {
                if (flag) {
                    flag = false;
                    start = LocalDateTime.parse("2014-01-01 00:00:00", dateTimeFormatter);
                    end = LocalDateTime.parse("2014-01-31 23:00:00", dateTimeFormatter);
//                    end = LocalDateTime.parse("2014-12-31 23:00:00", dateTimeFormatter);
                    break;
                }
                sendMsg(session, start, end, city);
                start = start.plusMonths(1);
//                start = start.plusYears(1);
                end = end.plusMonths(1);
//                end = end.plusYears(1);
                Thread.sleep(4000);
            }
            fileWriter.write(city + "===>" + start.format(dateTimeFormatter) + "/n");
            fileWriter.flush();
            start = LocalDateTime.parse("2014-01-01 00:00:00", dateTimeFormatter);
            end = LocalDateTime.parse("2014-01-31 23:00:00", dateTimeFormatter);
        }
    }

    public static void main(String[] args) throws IOException {
    }

    private void sendMsg(Session session, LocalDateTime start, LocalDateTime end, String city) throws IOException {
        Param param = new Param(city, "HOUR", start.format(dateTimeFormatter), end.format(dateTimeFormatter));
        ZqParam zqParam = new ZqParam(param, "CETCITYPERIOD");
        session.getBasicRemote().sendText(objectMapper.writeValueAsString(zqParam));
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class ZqParam {
        Param param;
        String method;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    static class Param {
        String city;
        String type;
        String startTime;
        String endTime;
    }


}
