package com.jhilaa.tribean.repository.configuration;

import com.jhilaa.tribean.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.web.util.matcher.AntPathRequestMatcher.antMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {

     @Autowired
     RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();// on disable pour nos test. Voir la
        //http.headers().frameOptions().disable();
        http.headers().frameOptions().disable();
        //http.headers().frameOptions().sameOrigin();
        http.headers().frameOptions().sameOrigin();
        http.exceptionHandling()
        //.authenticationEntryPoint(restAuthenticationEntryPoint)
        .authenticationEntryPoint(restAuthenticationEntryPoint)
        .and()
        .sessionManagement()
        // stateless = gestion des sessions c$oté client
        // token JWT => stateless (pas de session côté serveur, mais côté client)
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        // url autorisées
        .authorizeHttpRequests(auth -> auth
          //@TODO test
          .requestMatchers("**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/index.html").permitAll()
                .requestMatchers("/static/**").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/user")).permitAll()
                .requestMatchers(new AntPathRequestMatcher("/user/**")).permitAll()
                .requestMatchers("/test/**").permitAll()
                .requestMatchers("/authenticate").permitAll()
                .requestMatchers("/isConnected").permitAll()
                .requestMatchers("/v3/api-docs/**").permitAll()
                .requestMatchers("/api-docs.yaml").permitAll()
                .requestMatchers("/swagger-resources/**").permitAll()
                .requestMatchers("/swagger-ui/**").permitAll()
                .requestMatchers("/swagger-ui.html").permitAll()
                .requestMatchers("/webjars/**").permitAll()
                .requestMatchers("/v3/api-docs/swagger-config").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()
                .anyRequest().authenticated()
        );
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    // utilisateur avec mdp crypté et stocké en base
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }

}