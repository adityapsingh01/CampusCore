package com.collegeerp.config;

import com.collegeerp.handler.CustomAuthenticationSuccessHandler;
import com.collegeerp.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity // Enable @PrePost annotations if needed
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private CustomAuthenticationSuccessHandler authenticationSuccessHandler;

    @Bean
    public UserDetailsService userDetailsService() {
        return customUserDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity (enable in production with proper configuration)
            .userDetailsService(customUserDetailsService)
            .authorizeHttpRequests(authz -> authz
                    // Public URLs
                    .requestMatchers("/login", "/css/**", "/js/**", "/images/**", "/webjars/**").permitAll()
                    // HOD URLs
                    .requestMatchers("/hod/**").hasRole("HOD")
                    // Faculty URLs - accessible by FACULTY and HOD
                    .requestMatchers("/faculty/**").hasAnyRole("FACULTY", "HOD")
                    // Student URLs
                    .requestMatchers("/student/**").hasRole("STUDENT")
                    // Any other request must be authenticated
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                    .loginPage("/login")
                    .loginProcessingUrl("/login") // Default is /login, but we specify for clarity
                    .successHandler(authenticationSuccessHandler)
                    .permitAll()
            )
            .logout(logout -> logout
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
            );

        return http.build();
    }
}