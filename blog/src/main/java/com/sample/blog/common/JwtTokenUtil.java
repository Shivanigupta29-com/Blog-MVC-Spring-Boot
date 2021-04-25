package com.sample.blog.common;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.SignatureException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import io.jsonwebtoken.Jwts;
import org.springframework.util.StringUtils;

@Component
public class JwtTokenUtil {

    private Key key;

    public JwtTokenUtil(@Value("${jwt.secret}") String secretKey) {
        this.key = new SecretKeySpec(Base64.getDecoder().decode(secretKey),
                SignatureAlgorithm.HS256.getJcaName());
    }

    public String createToken(String username, String email){
        Instant now = Instant.now();
        return Jwts.builder()
                .claim("username", username)
                .claim("email", email)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(24l, ChronoUnit.HOURS)))
                .signWith(key)
                .compact();
    }

    public Jws<Claims> getTokenData(String token) throws SignatureException {
        if(token == null || token.length() == 0){
            throw new SignatureException("Empty Token");
        }
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token);

    }
}
