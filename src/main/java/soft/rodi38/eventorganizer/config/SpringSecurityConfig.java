package soft.rodi38.eventorganizer.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebMvc
@EnableMethodSecurity
@RequiredArgsConstructor
public class SpringSecurityConfig {


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Desabilitando CSRF para simplificação
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/events/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .httpBasic(httpBasic -> {}); // Configura HTTP Basic Authentication

        return http.build();
    }
}