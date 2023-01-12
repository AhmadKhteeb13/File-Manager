package com.example.filesmanegar.security.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@EnableWebSecurity
@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable();
        return http.build();
    }


}

















//public class SecurityConfig{
//    private final JwtAthFilter jwtAuthFilter;
//    private final AuthenticationProvider authenticationProvider;
//    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
//            new User(
//                    "Admin@gmail.com",
//                    "admain",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))
//            ),
//            new User(
//                    "User@gmail.com",
//                    "user",
//                    Collections.singleton(new SimpleGrantedAuthority("ROLE_USER"))
//            )
//    );
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/api/v1/**")
//                .permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and()
//                .authenticationProvider(authenticationProvider)
//                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//                .cors();
//        return http.build();
//    }
//
//
//}

//@Configuration
//@EnableWebSecurity
//@EnableWebMvc
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig  extends WebSecurityConfiguration {
//
//    @Bean
//    public BCryptPasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public DaoAuthenticationProvider daoAuthenticationProvider(){
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setUserDetailsService(userDetailsService());
//        provider.setPasswordEncoder(passwordEncoder());
//        return provider;
//    }
//
//    //@Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    //@Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http       //other configure params.
//                .csrf().disable();
//    }
//}