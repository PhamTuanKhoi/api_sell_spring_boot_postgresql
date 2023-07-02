package com.sell.tea.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sell.tea.dtos.request.product.CreateProductDto;
import com.sell.tea.entities.UserEntity;
import com.sell.tea.gobal.dtos.UserAttributeDto;
import com.sell.tea.security.JwtService;
import com.sell.tea.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
@Slf4j
public class UserInterceptor implements HandlerInterceptor {

    private final JwtService jwtService;
    private final UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!(StringUtils.hasText(authHeader) || (authHeader.startsWith("Bearer ")))) return true;

        String jwt = authHeader.substring(7);

        if (jwt.isEmpty() && !jwtService.validateToken(jwt)) return true;

        try {
            String email = jwtService.getUserEmailFromJwt(jwt);
            UserEntity user = userService.findByEmail(email);

            request.setAttribute("userId", user.getId());
        } catch (Exception ex) {
            log.error("Interceptor my write error: " + ex);
        }


        return true;
    }
}
