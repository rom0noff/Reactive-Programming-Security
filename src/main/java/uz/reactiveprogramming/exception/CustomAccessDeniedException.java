package uz.reactiveprogramming.exception;

import com.google.gson.Gson;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import uz.reactiveprogramming.model.AuthResponse;
import uz.reactiveprogramming.utils.EntryPoint;

import java.nio.charset.StandardCharsets;

import static org.springframework.http.HttpStatus.FORBIDDEN;

public class CustomAccessDeniedException extends RuntimeException implements ServerAccessDeniedHandler {
    public CustomAccessDeniedException(String message) {
        super(message);
    }

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {

        AuthResponse resultsDto = new AuthResponse(EntryPoint.ACCESS_DENIED, 403);
        byte[] bytes = new Gson().toJson(resultsDto).getBytes(StandardCharsets.UTF_8);

        ServerHttpResponse response = exchange.getResponse();
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        response.setStatusCode(FORBIDDEN);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        return response.writeWith(Mono.just(buffer));
    }
}
