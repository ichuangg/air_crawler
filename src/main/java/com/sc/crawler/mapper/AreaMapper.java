package com.sc.crawler.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AreaMapper {

    List<String> getCitys();

}
