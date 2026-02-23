package com.z.monitor;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Senior Architect Level Security Filter.
 * Validates the Z-Monitor Bearer token and sets up the SecurityContext
 * to allow access to Actuator endpoints without further authentication
 * if the request originates from the dashboard.
 */
public class ZMonitorSecurityFilter extends OncePerRequestFilter {

    private static final Set<String> validTokens = Collections.newSetFromMap(new ConcurrentHashMap<>());

    public static void addToken(String token) {
        validTokens.add(token);
    }

    public static void removeToken(String token) {
        validTokens.remove(token);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        
        String authHeader = request.getHeader("Authorization");
        
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            
            if (validTokens.contains(token)) {
                // Manually authenticate for this request
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        "z-monitor-admin", null, 
                        Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                
                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }
        
        filterChain.doFilter(request, response);
    }
}
