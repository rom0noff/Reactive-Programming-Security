package uz.reactiveprogramming.exception;

import com.google.gson.Gson;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import uz.reactiveprogramming.model.AuthResponse;
import uz.reactiveprogramming.utils.EntryPoint;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public class CustomUnauthorizedException extends RuntimeException implements ServerAuthenticationEntryPoint {
    public CustomUnauthorizedException(String message) {
        super(message);
    }

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        AuthResponse resultsDto = new AuthResponse(EntryPoint.UNAUTHORIZED, 401);
        byte[] bytes = new Gson().toJson(resultsDto).getBytes(StandardCharsets.UTF_8);

        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(UNAUTHORIZED);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(buffer));
    }
}
