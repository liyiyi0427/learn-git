package com.hexin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;



@SpringBootApplication
@ServletComponentScan//druid页面访问必须加这个
public class HexinSearchApiApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(HexinSearchApiApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HexinSearchApiApplication.class);
	}

}
