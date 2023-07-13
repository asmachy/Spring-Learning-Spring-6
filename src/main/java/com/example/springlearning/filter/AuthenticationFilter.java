package com.example.springlearning.filter;

import com.example.springlearning.service.UserDaoService;
import com.example.springlearning.utils.AuthUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import static org.apache.commons.lang3.StringUtils.isBlank;

@AllArgsConstructor
@Component
public class AuthenticationFilter extends OncePerRequestFilter {
    private AuthUtils authUtils;
    private UserDaoService userDetailsService;
    @SneakyThrows
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        checkJwtToken(request);
        filterChain.doFilter(request, response);
    }

    protected void checkJwtToken(HttpServletRequest request) {
        String authHeaderValue = request.getHeader("Authorization");
        if (isBlank(authHeaderValue) || !authHeaderValue.startsWith("Bearer "))
            return;
        if (SecurityContextHolder.getContext().getAuthentication() != null) return;
        String token = authHeaderValue.substring(7);
        String userEmail = authUtils.getEmailFromToken(token);
        UserDetails user = userDetailsService.loadUserByUsername(userEmail);
        Boolean validation = authUtils.validateToken(token);
        if (!validation)
            return;
        authUtils.setAuthentication(user, request);
    }

}
