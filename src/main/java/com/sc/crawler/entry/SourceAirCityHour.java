package com.sc.crawler.entry;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 城市小时数据表(T003AirCityHour)实体类
 *
 * @author makejava
 * @since 2022-10-17 10:59:24
 */
@Data
public class SourceAirCityHour implements Serializable {
    /**
     * 主键id
     */
    private String id;
    private String cityName;
    private String monitorTime;
    /**
     * AQI
     */
    private String complexindex;
    private String windlevel;
    private String winddirection;
    private String weather;


    private Double aqi;
    /**
     * 首要污染物
     */
    private String primaryPollutant;
    /**
     * PM10值（ug/M3）
     */
    private Double pm10;
    /**
     * PM10分指数
     */
    private Double pm10Iaqi;
    /**
     * PM2.5值（ug/M3）
     */
    private Double pm25;
    /**
     * PM2.5分指数
     */
    private Double pm25Iaqi;
    /**
     * 二氧化硫（ug/M3）
     */
    private Double so2;
    /**
     * 二氧化硫分指数
     */
    private Double so2Iaqi;
    /**
     * 二氧化氮（ug/M3）
     */
    private Double no2;
    /**
     * 二氧化氮分指数
     */
    private Double no2Iaqi;
    /**
     * 一氧化碳（mg/M3
     */
    private Double co;
    /**
     * 一氧化碳分指数
     */
    private Double coIaqi;
    /**
     * 臭氧（ug/m3）
     */
    private Double o3;
    /**
     * 臭氧分指数
     */
    private Double o3Iaqi;
    /**
     * 臭氧8小时
     */
    private Double o38h;
    /**
     * 空气质量等级
     */
    private String airQuality;
    /**
     * 空气质量类型
     */
    private String airQualityType;
    /**
     * 创建时间
     */
    private String createTime;
    /**
     * 更新时间
     */
    private String updateTime;
    /**
     * 数据状态（0：正常，1：失效）
     */
    private String status;
    /**
     * 数据来源
     */
    private String dataSource;
    /**
     * 计算规则（国省市镇办/15种）
     常见：国控、国省控、国省市、国省市镇办、镇办；默认：城市-国控、区县-镇办
     */
    private String computationRule;
    /**
     * 数据类型（0：实况，1：标况）
     */
    private String dataType;
    /**
     * 标记类型(H：有效数据不足;BB:连接不良；B：运行不良；W：等待数据恢复；HSp：数据超上限：LSp：数据超下限；：PS：跨度检查；PZ：零点检查；AS：精度检查；CZ：零点校准；CS：跨度校准；RM：自动或人工审核为无效)
     */
    private String tagType;

}

