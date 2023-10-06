package com.eazybytes.config;


import jakarta.servlet.DispatcherType;
import org.springframework.cglib.proxy.NoOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import javax.sql.DataSource;
import java.util.Collections;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //could use /myAccount/** to match every path inside myAccount

        http.cors(cors ->
                cors.configurationSource(
                        request -> {
                         CorsConfiguration config = new CorsConfiguration();
                         config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
                         config.setAllowedMethods(Collections.singletonList("*"));
                         config.setAllowCredentials(true);
                         config.setAllowedHeaders(Collections.singletonList("*"));
                         config.setMaxAge(3600L);
                         return config;
                        }
                        )
        );
        http.authorizeHttpRequests(requests ->
                        requests.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards", "/user").authenticated()
                                .requestMatchers("/notices","/contact","/register").permitAll()
                                 // o dispatcher error não era autorizado para usuários não logados, assim ele gerava um novo erro de unauthorized ()
                                // https://stackoverflow.com/questions/74971183/spring-security-6-0-authorizationfilter-questionable-default-for-shouldfiltera
                                // https://github.com/spring-projects/spring-security/issues/13321
                                .dispatcherTypeMatchers(DispatcherType.ERROR).permitAll()

                        )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }






}
