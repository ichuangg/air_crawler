package com.sc.crawler.mapper;

import com.sc.crawler.entry.SourceAirCityHour;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface HourMapper {

    void save(@Param("list") List<SourceAirCityHour> data);

}
