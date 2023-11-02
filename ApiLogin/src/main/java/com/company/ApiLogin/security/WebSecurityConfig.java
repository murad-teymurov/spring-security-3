package com.company.ApiLogin.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private final CustomUserDetailService customUserDetailService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http
                .cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.disable())
                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
                .sessionManagement(httpSecuritySessionManagementConfigurer ->
                        httpSecuritySessionManagementConfigurer
                                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin((form) -> form.disable()) // form.permitAll()
//                .formLogin((form) -> form.defaultSuccessUrl("/login"))
                .logout((logout) -> logout.disable()); // logout.permitAll()

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return  new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

//    @Bean
//    public SecurityFilterChain applicationSecurity(HttpSecurity http) throws Exception {
//
//        http
//                .cors()
//                .disable()
//                .csrf()
//                .disable()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .formLogin()
//                .disable()
//                .securityMatcher("/**")
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated()
//                );
//
//        return http.build();
//    }

//    @Bean
//    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/register/**").permitAll())
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/users/**").hasAnyAuthority("USER","ADMIN"))
//                .csrf(httpSecurityCsrfConfigurer -> httpSecurityCsrfConfigurer.disable())
//                .formLogin(withDefaults())
//                .httpBasic(withDefaults());
//        return http.build();
//    }


//    @Bean
//    public UserDetailsService userDetailsService() {
//        UserDetails user =
//                User.withDefaultPasswordEncoder()
//                        .username("user")
//                        .password("password")
//                        .roles("USER")
//                        .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}

