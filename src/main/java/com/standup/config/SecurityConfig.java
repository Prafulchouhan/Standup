package com.standup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    public static final String[] PUBLIC_URLS={
        "/api/v1/auth/**"
    };

    private static final String[] PRIVATE_URLS={
        "/api/v1/test"
    };
    @Autowired
    private JWTRequestFilter jwtRequestFilter;

    public SecurityConfig(JWTRequestFilter jwtRequestFilter) {
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable);
//        http.headers().frameOptions().disable().and().cors().and().csrf().disable();
        http.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class);
        http.
                authorizeHttpRequests(request ->{
                    request.requestMatchers(PUBLIC_URLS)
                            .permitAll()
                            .requestMatchers(PRIVATE_URLS)
                            .authenticated()
                            .anyRequest()
                            .permitAll();
                });

        return http.build();
    }

}

