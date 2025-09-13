package com.cheng.ecommercebackend.util;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtUtil {
    public static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long EXPIRATION = 1000 * 60 * 60 * 24; // 1 天
    private static final Set<String> blacklist = new HashSet<>();


    // 產生 token
    public static String createToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(Date.from(Instant.now().plusSeconds(EXPIRATION)))
                .signWith(key)
                .compact();
    }
    // 驗證 token
    public static String validateToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (JwtException e) {
            return null;
        }
    }
    //加入黑名單，ex,登出
    public static void addToBlackList(String token) {
//        blacklist.add(token);
        System.out.println(blacklist);
    }
    //是否在黑名單裡面
    public static boolean containsInBlackList(String token) {
        return blacklist.contains(token);
    }

}
