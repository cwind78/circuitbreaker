package com.circuit.breaker.demo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class ActuatorSecurityConfig {

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // @formatter:off
//        http
//                .requestMatcher(EndpointRequest.toAnyEndpoint().excluding("health", "info"))
//                .authorizeRequests()
//                .anyRequest().hasRole("ADMIN")
//
//                .and()
//
//                .httpBasic();
        // @formatter:on
//    }

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http.authorizeHttpRequests()
////                .requestMatchers("/act/**").authenticated()
////                .requestMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
////                .requestMatchers("/admin/**").hasRole("ADMIN")
//                .requestMatchers("/act/**").hasRole("ADMIN")
//                .anyRequest().permitAll()
//                .and().formLogin().loginPage("/login");
//        return http.build();
//    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//                .inMemoryAuthentication()
//                .withUser("admin")
//                .password("password")
//                .authorities("ROLE_ADMIN");
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }

}
