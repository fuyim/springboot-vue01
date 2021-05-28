package com.obtk.handler;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.obtk.util.jwt.JwtUtil;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class HandlerBean  implements HandlerInterceptor{


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //验证token
        Map<String,Object> map = new HashMap<>();
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            System.out.println("OPTIONS请求，放行");
            return true;
        }
        String token = request.getHeader("Authentication-Token");
        try {
            DecodedJWT decodeJwt = JwtUtil.getDecodeJwt(token);
            map.put("msg",true);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg","无效签名");
        }catch (TokenExpiredException e){
            map.put("msg","token失效");
        }catch (AlgorithmMismatchException e){
            map.put("msg","算法异常");
        }catch (Exception e){
            map.put("msg","认证失败");
        }

        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(map);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().println(json);
        return false;
    }
}
