package uz.reactiveprogramming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
@EnableR2dbcRepositories
public class ReactiveProgramming1Application {

    public static void main(String[] args) {
//        try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ReactiveProgramming1Application.class)){
//            context.getBean(DisposableServer.class).onDispose().block();
//        }
        SpringApplication.run(ReactiveProgramming1Application.class);
    }

//    @Bean
//    public DisposableServer disposableServer(ApplicationContext context){
//        HttpHandler handler = WebHttpHandlerBuilder.applicationContext(context).build();
//        ReactorHttpHandlerAdapter adapter = new ReactorHttpHandlerAdapter(handler);
//        HttpServer httpServer = HttpServer.create().host("localhost").port(8082);
//        return httpServer.handle(adapter).bindNow();
//    }
}