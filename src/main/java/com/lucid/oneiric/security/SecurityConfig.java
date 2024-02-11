package com.lucid.oneiric.security;

import com.lucid.oneiric.services.OneiricUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {
    @Bean
    public Argon2PasswordEncoder argon2PasswordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 4096, 3);
    }

    @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/login").permitAll()
                            .anyRequest().authenticated()
                    );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(OneiricUserDetailsService oneiricUserDetailsService, Argon2PasswordEncoder argon2PasswordEncoder) {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(oneiricUserDetailsService);
        authenticationProvider.setPasswordEncoder(argon2PasswordEncoder);

        List<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(authenticationProvider);

        return new ProviderManager(providers);
    }

}
