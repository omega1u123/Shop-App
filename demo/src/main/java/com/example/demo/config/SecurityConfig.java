package com.example.demo.config;

import com.example.demo.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;

@Configuration
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .requestMatchers("/registration/*").permitAll()
                .requestMatchers("/css/**").permitAll()
                .requestMatchers("/admin/*").hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin(login -> login
                        .loginPage("/auth/loginPage").permitAll()
                        .defaultSuccessUrl("/catalog/catalogPage", true)
                        .successHandler((request, response, authentication) -> {
                            // Получаем роли пользователя из аутентификации
                            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                            for (GrantedAuthority authority : authorities) {
                                if (authority.getAuthority().equals("ADMIN")) {
                                    // Если пользователь имеет роль ADMIN, перенаправляем на другую страницу
                                    response.sendRedirect("/admin/adminPage");
                                    return;
                                }
                            }
                            // Если роль не является ADMIN, перенаправляем на обычную страницу
                            response.sendRedirect("/catalog/catalogPage");
                        })
                        .permitAll())
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/auth/loginPage")
                        .permitAll());


        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService)
                .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
}
