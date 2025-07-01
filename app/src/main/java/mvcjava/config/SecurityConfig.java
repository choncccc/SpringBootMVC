package mvcjava.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                    .requestMatchers("/css/**", "/webjars/**").permitAll()
                    .anyRequest().authenticated())
            .formLogin(Customizer.withDefaults())   // default /login page
            .logout(Customizer.withDefaults());
        return http.build();
    }

    // Two demo users: user/user  |  admin/admin
    @Bean
    UserDetailsService users() {
        return new InMemoryUserDetailsManager(
            User.withUsername("user").password("{noop}user").roles("USER").build(),
            User.withUsername("admin").password("{noop}admin").roles("ADMIN").build()
        );
    }
}
