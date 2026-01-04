package com.medori42.platformcore.securityconfig;
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
public class PlatformSecurityConfig {
    private static final String PUBLIC_API_PATTERN = "/api
    public PlatformSecurityConfig() {
    }
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        configureCors(http);
        configureCsrf(http);
        configureAuthorization(http);
        return http.build();
    }
    private void configureCors(final HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(corsConfigurationSource()));
    }
    private void configureCsrf(final HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
    }
    private void configureAuthorization(final HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth ->
                auth.requestMatchers(PUBLIC_API_PATTERN).permitAll()
                    .anyRequest().authenticated()
        );
    }
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(ALLOW_ALL_ORIGINS));
        config.setAllowedMethods(ALLOWED_HTTP_METHODS);
        config.setAllowedHeaders(Arrays.asList(ALLOW_ALL_HEADERS));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration(ALL_ROUTES_PATTERN, config);
        return source;
    }
}