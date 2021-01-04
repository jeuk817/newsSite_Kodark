package com.kodark.news.service.impl;

import java.security.Key;
import java.util.Date;
import java.util.Map;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.kodark.news.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JwtServiceImpl implements JwtService {
	
	@Autowired
	Environment env;
	 
    @Override
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
        
//        builder.setExpiration(new Date(expirationMillis));
        return builder.compact();
    }
 
    @Override
    public Claims getClaims(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(env.getProperty("secret.jwt")))
                .parseClaimsJws(token).getBody();
        return claims;
    }
}
