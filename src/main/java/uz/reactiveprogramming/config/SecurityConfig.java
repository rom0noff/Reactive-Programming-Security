package uz.reactiveprogramming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import uz.reactiveprogramming.exception.CustomAccessDeniedException;
import uz.reactiveprogramming.exception.CustomUnauthorizedException;
import uz.reactiveprogramming.service.UserService;
import uz.reactiveprogramming.utils.EntryPoint;

import java.nio.file.AccessDeniedException;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http){
        return http
                .exceptionHandling()
                .authenticationEntryPoint(
                        new CustomUnauthorizedException(EntryPoint.UNAUTHORIZED)
                )
                .accessDeniedHandler(
                        new CustomAccessDeniedException(EntryPoint.ACCESS_DENIED)
                )
                .and()
                .authorizeExchange()
                .pathMatchers("/message")
                .hasRole("ADMIN")
                .pathMatchers("/create")
                .permitAll()
                .pathMatchers("/getAll")
                .hasRole("ADMIN")
                .anyExchange()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf()
                .disable()
                .build();
    }
//    @Bean
//    public MapReactiveUserDetailsService userDetailsService(){
//        UserDetails userDetails = User
//                .withUsername("admin")
//                .password(passwordEncoder().encode("1234"))
//                .roles("ADMIN")
//                .build();
//        return new MapReactiveUserDetailsService(userDetails);
//    }

    @Bean
    ReactiveAuthenticationManager authenticationManager(UserService userService){
        return new UserDetailsRepositoryReactiveAuthenticationManager(userService);
    }
}
