package uz.reactiveprogramming.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class CustomWebFilter implements WebFilter {
    private final Logger logger = LogManager.getLogger(CustomWebFilter.class);
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        long startTime = System.currentTimeMillis();

        return chain.filter(exchange).doFinally(signalType -> {
            long totalTime= System.currentTimeMillis() - startTime;

            exchange.getAttributes().put("totalTime", totalTime);

            StringBuilder logMessage = new StringBuilder();
            logMessage.append("\n");
            logMessage.append("<----------------------Actions in progress----------------------->");
            logMessage.append("\n");
            logMessage.append("Took: ").append(totalTime).append(" ms").append("\n");
            logMessage.append("Method: ").append(exchange.getRequest().getMethod()).append("\n");
            logMessage.append("URI: ").append(exchange.getRequest().getURI()).append("\n");
            logMessage.append("Status: ").append(exchange.getResponse().getStatusCode()).append("\n");
            logMessage.append("RemoteAddress: ").append(exchange.getRequest().getRemoteAddress()).append("\n");
            logMessage.append("<---------------------------------------------------------------->");


            logger.info(logMessage);
        });
    }
}
