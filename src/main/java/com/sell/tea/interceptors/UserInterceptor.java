package com.sell.tea.interceptors;

import com.sell.tea.entities.UserEntity;
import com.sell.tea.security.JwtService;
import com.sell.tea.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
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
        if (request.getMethod().equalsIgnoreCase("POST")) {
            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || authHeader.isEmpty()) {
                return this.removeAttribute(request);
            }

            String jwt = authHeader.substring(7);

            if (!jwtService.validateToken(jwt))
                return this.removeAttribute(request);

            try {
                String email = jwtService.getUserEmailFromJwt(jwt);
                UserEntity user = userService.findByEmail(email);

                request.setAttribute("userId", user.getId());
                request.setAttribute("user", user);
                return true;
            } catch (Exception ex) {
                log.error("Interceptor my write error: " + ex);
            }

            return true;
        }
        return true;
    }

    public boolean removeAttribute(HttpServletRequest request) {
        request.removeAttribute("userId");
        request.removeAttribute("user");

        return true;
    }
}
