package com.medori42.cloudapp.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import java.util.Arrays;
import java.util.List;
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration {
    private static final String PUBLIC_API_PATTERN = "/api
    private static final String ALL_ROUTES_PATTERN = "
    private static final String ALLOW_ALL_ORIGINS = "*";
    private static final String ALLOW_ALL_HEADERS = "*";
    private static final List<String> ALLOWED_HTTP_METHODS = Arrays.asList(
            "GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"
    );
    public WebSecurityConfiguration() {
    }
    @Bean
    public SecurityFilterChain configureSecurityFilterChain(
            final HttpSecurity httpSecurityBuilder) throws Exception {
        configureCorsSecurity(httpSecurityBuilder);
        configureCsrfProtection(httpSecurityBuilder);
        configureRequestAuthorization(httpSecurityBuilder);
        return httpSecurityBuilder.build();
    }
    private void configureCorsSecurity(
            final HttpSecurity httpSecurityBuilder) throws Exception {
        httpSecurityBuilder.cors(corsCustomizer ->
            corsCustomizer.configurationSource(createCorsConfigurationSource())
        );
    }
    private void configureCsrfProtection(
            final HttpSecurity httpSecurityBuilder) throws Exception {
        httpSecurityBuilder.csrf(AbstractHttpConfigurer::disable);
    }
    private void configureRequestAuthorization(
            final HttpSecurity httpSecurityBuilder) throws Exception {
        httpSecurityBuilder.authorizeHttpRequests(authorizationConfigurer ->
            authorizationConfigurer
                .requestMatchers(PUBLIC_API_PATTERN).permitAll()
                .anyRequest().authenticated()
        );
    }
    @Bean
    public CorsConfigurationSource createCorsConfigurationSource() {
        CorsConfiguration corsConfig = buildCorsConfiguration();
        UrlBasedCorsConfigurationSource corsConfigSource =
            new UrlBasedCorsConfigurationSource();
        corsConfigSource.registerCorsConfiguration(ALL_ROUTES_PATTERN, corsConfig);
        return corsConfigSource;
    }
    private CorsConfiguration buildCorsConfiguration() {
        CorsConfiguration corsConfig = new CorsConfiguration();
        corsConfig.setAllowedOrigins(Arrays.asList(ALLOW_ALL_ORIGINS));
        corsConfig.setAllowedMethods(ALLOWED_HTTP_METHODS);
        corsConfig.setAllowedHeaders(Arrays.asList(ALLOW_ALL_HEADERS));
        return corsConfig;
    }
}