package com.hotelbooking.hotelbooking.modules.services;

import com.hotelbooking.hotelbooking.utils.SecretKeyStringConverter;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;

@Service
public class JwtService {
    final String JWT_SECRET = "/nIXb47CO+j9vm4YK55c4JHyMX8i/agIaP/XzqfP1qs=";
    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }
    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }
    private Claims extractAllClaims(String token){
        return (Claims) Jwts.parser()
                .verifyWith(SecretKeyStringConverter.stringToSecretKey(JWT_SECRET, "HS256"))
                .build()
                .parseSignedClaims(token);
    }

    public String generateToken(Map<String, Object> claims, UserDetails userDetails){
        return Jwts.builder().claims(claims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(SecretKeyStringConverter.stringToSecretKey(JWT_SECRET, "HS256"))
                .compact();
    }
    public String generateToken(UserDetails userDetails){
        return Jwts.builder()
                .claims(new HashMap<>())
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+1000*60*24))
                .signWith(SecretKeyStringConverter.stringToSecretKey(JWT_SECRET, "HS256"))
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String username= extractUsername(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }
    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }
}
