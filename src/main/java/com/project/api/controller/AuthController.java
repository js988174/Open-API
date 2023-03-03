package com.project.api.controller;

import com.project.api.repository.MemberRepository;
import com.project.api.response.SessionResponse;
import com.project.api.vo.MemberVo;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.SecretKey;
import javax.transaction.Transactional;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final String KEY = "7k+H0TaK9i5MEf88uvsfuyBUzxfM+HX+5WUykOwYLW3uWcI1SH6R5y+jOZq8q0R6KTAvSVIKUkooxxvrwN+ixQ==";

    @PostMapping("/auth/login")
    public SessionResponse login(@RequestBody MemberVo memberVo) {

        SecretKey key = Keys.hmacShaKeyFor(Base64.getDecoder().decode(KEY));

        String jws = Jwts.builder()
                .setSubject(String.valueOf(memberVo.getId()))
                .setExpiration(new Date(System.currentTimeMillis()+1*(1000*60*60*24*365)))
                .signWith(key)
                .compact();

        return new SessionResponse(jws);
    }

}
