package com.zzn.apprecieve.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@MapperScan({"com.zzn.apprecieve.mapper"})
@Configuration
public class MybatisConfig {

}