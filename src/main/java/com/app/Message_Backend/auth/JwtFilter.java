package com.app.Message_Backend.auth;

import com.app.Message_Backend.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtils jwtUtils;

    public JwtFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        Optional<String> potentialToken = Optional.ofNullable(jwtUtils.getToken(request));
        System.out.println(request.getRequestURL());
        System.out.println(request.getParameterNames().toString());
        if(!potentialToken.isPresent()) {
            filterChain.doFilter(request, response);
            return;
        }

        UserDetailsImp userDetails = jwtUtils.validate(potentialToken.get());
        WebAuthenticationDetails details = new WebAuthenticationDetails(request);

        SecurityContextHolder.getContext().setAuthentication(new JWTPreAuthenticationToken(userDetails, details));
        filterChain.doFilter(request, response);
    }

}
