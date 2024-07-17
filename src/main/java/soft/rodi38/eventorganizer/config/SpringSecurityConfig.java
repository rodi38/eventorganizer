package soft.rodi38.eventorganizer.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import soft.rodi38.eventorganizer.security.jwt.JwtAuthEntryPoint;
import soft.rodi38.eventorganizer.security.jwt.JwtAuthTokenFilter;
import soft.rodi38.eventorganizer.service.details.UserDetailsServiceImpl;


@Configuration
@EnableMethodSecurity
@AllArgsConstructor
public class SpringSecurityConfig {

    UserDetailsServiceImpl userDetailsService;

    private JwtAuthEntryPoint jwtUnauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();

        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(jwtUnauthorizedHandler))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth ->
                        auth.requestMatchers("/api/auth/**", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/api/donation").hasRole("ATTENDEE")
                                .requestMatchers(HttpMethod.POST, "/api/events").hasRole("ORGANIZER")

                                .requestMatchers(HttpMethod.GET, "/api/events").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/events/{id}").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/events/{name}").permitAll()


                                .requestMatchers(HttpMethod.GET, "/api/organizers").hasRole("ORGANIZER")
                                .requestMatchers(HttpMethod.GET, "/api/organizers/{id}").hasRole("ORGANIZER")
                                .requestMatchers(HttpMethod.GET, "/api/attendees").hasRole("ATTENDEE")
                                .requestMatchers(HttpMethod.GET, "/api/attendees/{id}").hasRole("ATTENDEE")

                                .requestMatchers(HttpMethod.PUT, "/api/organizers").hasRole("ORGANIZER")
                                .requestMatchers(HttpMethod.PUT, "/api/attendees").hasRole("ATTENDEE")
                                .requestMatchers(HttpMethod.PUT, "/api/events").hasAnyRole("ATTENDEE", "ORGANIZER")

                                .requestMatchers(HttpMethod.DELETE, "/api/organizers/{id}").hasRole("ORGANIZER")
                                .requestMatchers(HttpMethod.DELETE, "/api/attendees/{id}").hasRole("ATTENDEE")
                                .requestMatchers(HttpMethod.DELETE, "/api/events/{id}").hasRole("ORGANIZER")


                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}