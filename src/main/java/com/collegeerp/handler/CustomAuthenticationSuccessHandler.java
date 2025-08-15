package com.collegeerp.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // Determine the redirect URL based on user roles
        String redirectUrl = determineTargetUrl(authentication.getAuthorities());

        // Redirect to the appropriate dashboard
        response.sendRedirect(redirectUrl);
    }

    private String determineTargetUrl(Collection<? extends org.springframework.security.core.GrantedAuthority> authorities) {
        boolean isHOD = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_HOD"));
        boolean isFaculty = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_FACULTY"));
        boolean isStudent = authorities.stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_STUDENT"));

        if (isHOD) {
            return "/hod/dashboard";
        } else if (isFaculty) {
            return "/faculty/dashboard";
        } else if (isStudent) {
            return "/student/dashboard";
        } else {
            // Default fallback
            return "/login?error";
        }
    }
}