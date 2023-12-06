package com.example.jwttest.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class JwtTokenizerTest {

    @Value("${jwt.secretKey}")
    String accessSecret;
//    String accessSecret = "1234567890a1234567890a1234567890";
    public final long ACCESS_TOKEN_EXPIRE_COUNT = 30 * 60 * 1000L;
    //30분 설정, 분 초 밀리세컨드
    @Test
    public void createToken() throws Exception {

        String email = "abc@abc.com";
        List<String> roles = List.of("ROLE_USER");
        Long id = 1L;
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("roles", roles);
        claims.put("userId", id);

        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);

        String JwtToken = Jwts.builder() //builder는 JwtBulder을 반환
                .setClaims(claims) //claims가 추가된 JwtBulder를 리턴
                .setIssuedAt(new Date()) //
                .setExpiration(new Date(new Date().getTime() + this.ACCESS_TOKEN_EXPIRE_COUNT)) //현재시간으로부터 30분뒤 만료
                .signWith(Keys.hmacShaKeyFor(accessSecret)) //서명추가
                .compact();

        System.out.println(JwtToken);

    }

    @Test
    public void parseToken() throws Exception {
        byte[] accessSecret = this.accessSecret.getBytes(StandardCharsets.UTF_8);
        String jwtToken = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhYmNAYWJjLmNvbSIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJ1c2VySWQiOjEsImlhdCI6MTcwMTg2NTQ3OCwiZXhwIjoxNzAxODY3Mjc4fQ.lsMyEy2Smq-0ofw4rvbds03LYlmEnN--upyNM-8eJ30";

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(accessSecret))
                .build()
                .parseClaimsJws(jwtToken)
                .getBody();
        System.out.println(claims.getSubject());
        System.out.println(claims.get("roles"));
        System.out.println(claims.get("userId"));
        System.out.println(claims.getIssuedAt());
        System.out.println(claims.getExpiration());
    }
}
