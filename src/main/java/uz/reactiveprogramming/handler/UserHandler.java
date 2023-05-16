package uz.reactiveprogramming.handler;

import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import uz.reactiveprogramming.model.AuthResponse;
import uz.reactiveprogramming.model.User;
import uz.reactiveprogramming.service.HumanService;

import static uz.reactiveprogramming.encoder.PassEncoder.passwordEncoder;

@Component
public class UserHandler {
    private final HumanService service;

    public UserHandler(HumanService service) {
        this.service = service;
    }
    public Mono<ServerResponse> create(ServerRequest serverRequest){
        Mono<User> userMono = serverRequest.bodyToMono(User.class);
        return userMono.map(
                user -> {
                    user.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(user.getPassword()));
                    return user;
                })
                .flatMap(
                        user ->
                                service
                                        .getByUsername(user.getUsername())
                                        .flatMap(
                                                dbUser ->
                                                        ServerResponse.badRequest()
                                                                .body(
                                                                        BodyInserters.fromValue(
                                                                                new AuthResponse("User already exist", 400))))
                                        .switchIfEmpty(
                                                service
                                                        .create(user)
                                                        .map(savedUser -> {
                                                            savedUser.setPassword(null);
                                                            return savedUser;
                                                        })
                                                        .flatMap(
                                                                savedUser -> ServerResponse.ok()
                                                                        .contentType(MediaType.APPLICATION_JSON)
                                                                        .body(BodyInserters.fromValue(savedUser))
                                                                        .onErrorResume(Mono::error))));
    }

    public Mono<ServerResponse> getAllUsers(ServerRequest serverRequest){
        return service
                .findAll()
                .collectList()
                .flatMap(r ->ServerResponse.ok().bodyValue(r).onErrorResume(Mono::error));
    }
}
