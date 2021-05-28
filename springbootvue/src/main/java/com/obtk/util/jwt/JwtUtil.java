package com.obtk.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;
import java.util.Map;

public class JwtUtil {

    private static final String sign = "!e324obtk";

    public static String getToken(Map<String,String> map){
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE,7);
        JWTCreator.Builder builder = JWT.create();
        map.forEach((k,v)->{
            builder.withClaim(k,v);
        });
        String token = builder.withExpiresAt(calendar.getTime()).sign(Algorithm.HMAC512(JwtUtil.sign));
        System.out.println(token);
        return token;
    }

    public static DecodedJWT getDecodeJwt(String token){
        DecodedJWT verify = null;
            JWTVerifier verifier = JWT.require(Algorithm.HMAC512(sign)).build();
            verify = verifier.verify(token);
        return verify;
    }

    public static int getParams(String token){
        DecodedJWT decodeJwt = getDecodeJwt(token);
        String idStr = decodeJwt.getClaim("id").asString();
        int id = Integer.parseInt(idStr);
        return id;
    }

}
