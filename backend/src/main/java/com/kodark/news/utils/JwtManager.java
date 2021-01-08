package com.kodark.news.utils;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.kodark.news.controller.advice.exceptions.UnauthorizedException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/*
 * title : jwt를 처리하는 클래스
 * dec : jwt를 만들고, 파싱한다.
 * 작성자 : 류제욱
 * 작성일 : 2020-01-06
 */
@Component
public class JwtManager {

	@Autowired
	Environment env;
	
	// jwt를 만든다.
    public String createJwt(String subject, Map<String, Object> claims, long ttlMillis) {
        if (ttlMillis <= 0) {
            throw new RuntimeException("Expiry time must be greater than Zero : ["+ttlMillis+"] ");
        }
        
        SignatureAlgorithm  signatureAlgorithm= SignatureAlgorithm.HS256;
        byte[] secretKeyBytes = DatatypeConverter.parseBase64Binary(env.getProperty("secret.jwt"));
        Key signingKey = new SecretKeySpec(secretKeyBytes, signatureAlgorithm.getJcaName());
        long expirationMillis = System.currentTimeMillis() + ttlMillis;
        
        JwtBuilder builder = Jwts.builder()
                .setSubject(subject)
                .signWith(signatureAlgorithm, signingKey)
                .setExpiration(new Date(expirationMillis));
        for(Map.Entry<String, Object> entry : claims.entrySet()) {
        	builder.claim(entry.getKey(), entry.getValue());
        }
        
        return builder.compact();
    }
    
    // jwt를 파싱한다.
    public Claims getClaims(String token) {
    	try {
    		Claims claims = Jwts.parser()
    				.setSigningKey(DatatypeConverter.parseBase64Binary(env.getProperty("secret.jwt")))
    				.parseClaimsJws(token).getBody();
    		return claims;    		
    	} catch(JwtException e) {
    		throw new UnauthorizedException(e);
    	}
    }
}
