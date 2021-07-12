package com.example.springjwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {
    private String SECRET_KEY="sachin@123";
    //create Token
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+60*60*60*10))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY).compact();
    }
    //generate Token
    public String generateToken(UserDetails userDetails)
    {
        Map<String,Object> claims=new HashMap<>();
        return createToken(claims,userDetails.getUsername());
    }

    //Extract Claims i.e payload keys like "username":"sachin@gmail.com" then "username" is a claim
    public Claims extractAllClaims(String token)
    {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    public <T> T extractClaims(String token, Function<Claims,T> claimsResolver)
    {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token)
    {
        return extractClaims(token,Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return extractClaims(token,Claims::getExpiration);
    }

    public boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    public boolean validToken(String token,UserDetails userDetails)
    {
        final String username=extractUsername(token);
        if(username.equals(userDetails.getUsername()) && !isTokenExpired(token))
            return true;
        return false;
    }
}
