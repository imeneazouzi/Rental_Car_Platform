package com.RentalCar.location.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF pour les APIs REST
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/cars/**").authenticated() // Nécessite une authentification
                        .anyRequest().permitAll() // Autorise les autres endpoints
                )
                .httpBasic(httpBasic -> httpBasic.realmName("RentalCar")) // HTTP Basic avec un nom de domaine personnalisé
                .logout(logout -> logout.logoutUrl("/logout").logoutSuccessHandler((request, response, authentication) -> {
                    response.setStatus(200);
                })); // Configuration de déconnexion sans méthode dépréciée

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        return new InMemoryUserDetailsManager(
                User.withUsername("client")
                        .password(passwordEncoder.encode("password"))
                        .roles("CLIENT")
                        .build(),
                User.withUsername("owner")
                        .password(passwordEncoder.encode("password"))
                        .roles("OWNER")
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
