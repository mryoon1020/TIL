package com.example.kakao_map.controler;

import com.example.kakao_map.service.KakaoMapService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class KakaoMapControler {

    private static final Logger log = LoggerFactory.getLogger(KakaoMapControler.class);

    @Autowired
    @Qualifier("com.example.kakao_map.service.KakaoMapServiceImpl")
    private KakaoMapService service;

    @Value("${kakaokey}")
    private String kakaokey;

    @GetMapping("/")
    public String home(HttpServletRequest request){
    System.out.println(kakaokey);
    request.setAttribute("kakaokey",kakaokey);
        return "main";

    }

}
