package com.eazybytes.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        //could use /myAccount/** to match every path inside myAccount
        http.authorizeHttpRequests(requests ->
                        requests.requestMatchers("/myAccount", "/myBalance", "/myLoans", "/myCards").authenticated()
                                .requestMatchers("/notices", "/contact").permitAll()
                        )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return http.build();

    }

    /** if i want to deny every request returning 403 (first i have to auth then gonna deny)
     * @Bean
     *     SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
     *
     *         http.authorizeHttpRequests(requests ->
     *                         requests.anyRequest.denyAll()
     *
     *                         )
     *                 .formLogin(Customizer.withDefaults())
     *                 .httpBasic(Customizer.withDefaults());
     *         return http.build();
     *
     *     }
     */

}