package com.kyungjoon.interceptors;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class InterceptorOne implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, @NotNull HttpServletResponse res, @NotNull Object handler) throws Exception {

        String authorizationHeaderValue = request.getHeader("Authorization");
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer")) {
            String token = authorizationHeaderValue.substring(7, authorizationHeaderValue.length());
            System.out.println("token===>" + token);
            return true;
        } else {//todo : 해더가 없는 경우 인증이 안된 api호출 이므로 return false.!
//            ObjectMapper mapper = new ObjectMapper();
//            res.setContentType("application/json");
//            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//            res.getWriter().write(mapper.writeValueAsString("no auth"));
//            System.out.println("preHandle!!!");
//            return false;
            return true;
        }

    }

    @Override
    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
