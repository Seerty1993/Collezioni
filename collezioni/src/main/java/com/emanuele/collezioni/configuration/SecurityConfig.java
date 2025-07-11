package com.emanuele.collezioni.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1) Non teniamo sessioni (stateless)
                .sessionManagement(sm -> sm
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )

                // 2) Disabilitiamo CSRF (tipico per API REST chiamate via client JS)
                .csrf(csrf -> csrf.disable())

                // 3) CORS (se ti serve)
                .cors(Customizer.withDefaults())

                // 4) Regole di autorizzazione
                .authorizeHttpRequests(auth -> auth
                        // Permettiamo il preflight CORS
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()

                        // Swagger/OpenAPI e risorse collegate
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/webjars/**"
                        ).permitAll()

                        // Tutte le altre richieste devono essere autenticate
//                        .anyRequest().authenticated()
                );

                // 5) HTTP Basic (realm opzionale)
//                .httpBasic(basic -> basic
//                        .realmName("Collezioni API")
//                );

        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var user = User.withUsername("user")
                .password(encoder.encode("password"))
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}
