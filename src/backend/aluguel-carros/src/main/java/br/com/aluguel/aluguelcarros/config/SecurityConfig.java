package br.com.aluguel.aluguelcarros.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // Habilita a segurança web do Spring no nosso projeto
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean 
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers(
                    "/login", 
                    "/cadastro", 
                    "/css/**", 
                    "/js/**", 
                    "/images/**"
                ).permitAll() 
                .anyRequest().authenticated() 
            )
            .formLogin(form -> form
                .loginPage("/login") 
                .loginProcessingUrl("/login") 
                .defaultSuccessUrl("/catalogo", true) 
                .permitAll() 
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout") 
                .permitAll()
            );

        return http.build();
    }
}