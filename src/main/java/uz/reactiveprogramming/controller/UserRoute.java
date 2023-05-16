package uz.reactiveprogramming.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.*;
import uz.reactiveprogramming.handler.UserHandler;

@Configuration
public class UserRoute {
    @Bean
    @SuppressWarnings("NullableProblem")
    RouterFunction<?> routes(UserHandler handler){
        return RouterFunctions.route(RequestPredicates.POST("/create"), handler::create)
                .andRoute(RequestPredicates.GET("/getAll"), handler::getAllUsers);
    }
}
