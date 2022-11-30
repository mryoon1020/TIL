package com.example.kakao_map;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages= {"com.example.kakao_map.*"})
public class KakaoMapApplication {

	public static void main(String[] args) {
		SpringApplication.run(KakaoMapApplication.class, args);
	}

}
