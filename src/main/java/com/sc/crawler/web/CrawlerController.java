package com.sc.crawler.web;

import com.sc.crawler.entry.HourRoot;
import com.sc.crawler.entry.SourceAirCityHour;
import com.sc.crawler.mapper.HourMapper;
import com.sc.crawler.util.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@ResponseBody
public class CrawlerController {

    @Autowired
    RestTemplate restTemplate;

    @PostMapping("/test1")
    public Object t1(String param) {
        System.out.println(param);
        String url = "https://www.zq12369.com/api/newzhenqiapi.php";
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        header.add("Cookie", "UM_distinctid=18465b36cf124d-0625ce1a69431d-26021e51-1fa400-18465b36cf23e5; usertype=POINT; usercenter=117.25944%2C36.418107; userzoom=8; dcity1=%E5%8C%97%E4%BA%AC; dcity2=%E4%B8%8A%E6%B5%B7; dcity3=%E6%9D%AD%E5%B7%9E; city=%E6%B5%8E%E5%8D%97; CNZZDATA1254317176=735573162-1668151144-https%253A%252F%252Fwww.google.com%252F%7C1668489363; SECKEY_ABVK=eHU5Xc25Q5zE6F51lCniVyiU9fq3xub74v3fkhHwaPU%3D; BMAP_SECKEY=hmCEfzH2GLWnbwDjUmh3Ig6tGTuGRh0Ymv9K_dMR-znoNliN8dlLdAHnTpnqIkT3OocBcPq4jLrUN6X1rD-cZQ2asUfXq9kJSIdLNBN6dKsIzZXVbtHtVtTKIYQhGZVjb3trzPuhBhRZZTQ84oW1_2G7v8JENLoV5kuE_IJQ_TfR7wBpC0G7MjCYEWx4ocCx");
        header.add("Host", "www.zq12369.com");
        header.add("Origin", "https://www.zq12369.com");
        header.add("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/107.0.0.0 Safari/537.36");
        header.add("Referer", "https://www.zq12369.com/environment.php?city=%E6%B5%8E%E5%8D%97&tab=city");
        header.add("Content-Length", String.valueOf(param.toCharArray().length));
        param = "param=" + param;
        HttpEntity<String> http = new HttpEntity<>(param, header);
        return restTemplate.postForObject(url, http, String.class);
    }

    @Autowired
    private HourMapper hourMapper;

    @PostMapping("test2/{city}")
    public void t2(String data, @PathVariable String city) throws Exception {
        HourRoot hourRoot = JsonUtils.json2pojo(data, HourRoot.class);
        if (hourRoot.getSuccess()) {
            List<HourRoot.HourData> rows = hourRoot.getResult().getData().getRows();
            int total = hourRoot.getResult().getData().getTotal();
            List<SourceAirCityHour> sourceAirCityHours = convertObject(rows, city);
            hourMapper.save(sourceAirCityHours);
            System.out.println("成功存入:"+total);
        }
    }

    static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");


    @GetMapping("startTime/{startTime}")
    public String startTime(@PathVariable String startTime) {
        LocalDate parse = LocalDate.parse(startTime, dateTimeFormatter);
        LocalDate localDate = parse.plusMonths(1);
        return localDate.format(dateTimeFormatter);
    }
    //{"startTime":"2014-01-01","endTime":"2014-02-01"}

    @GetMapping("endTime/{endTime}")
    public String endTime(@PathVariable String endTime) {
        LocalDate parse = LocalDate.parse(endTime, dateTimeFormatter);
        LocalDate localDate = parse.plusMonths(1);
        return localDate.format(dateTimeFormatter);
    }
    private List<SourceAirCityHour> convertObject(List<HourRoot.HourData> rows,String city) {
        List<SourceAirCityHour> res = new ArrayList<>();
        for (HourRoot.HourData row : rows) {
            SourceAirCityHour sourceAirCityHour = new SourceAirCityHour();
            sourceAirCityHour.setId(UUID.randomUUID().toString());
            sourceAirCityHour.setAqi(Double.valueOf(row.getAqi()));
            sourceAirCityHour.setPm10(Double.valueOf(row.getPm10()));
            sourceAirCityHour.setPm25(Double.valueOf(row.getPm2_5()));
            sourceAirCityHour.setCo(Double.valueOf(row.getCo()));
            sourceAirCityHour.setO3(Double.valueOf(row.getO3()));
            sourceAirCityHour.setNo2(Double.valueOf(row.getNo2()));
            sourceAirCityHour.setSo2(Double.valueOf(row.getSo2()));
            sourceAirCityHour.setCityName(city);
            sourceAirCityHour.setMonitorTime(row.getTime());
            sourceAirCityHour.setComplexindex(row.getComplexindex());
            sourceAirCityHour.setWeather(row.getWeather());
            sourceAirCityHour.setWinddirection(row.getWinddirection());
            sourceAirCityHour.setWindlevel(row.getWindlevel());
            res.add(sourceAirCityHour);
        }
        return res;
    }

}
