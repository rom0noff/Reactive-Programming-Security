package uz.reactiveprogramming.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.reactiveprogramming.jwt.JwtUtil;
import uz.reactiveprogramming.model.User;
import uz.reactiveprogramming.repository.UserRepository;

@Service
public class HumanService {
    private final UserRepository userRepository;
    public HumanService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    public Mono<User> create(User user){
        return userRepository.save(user)
                .onErrorMap(e -> new RuntimeException());
    }
    public Flux<User> findAll(){
        return userRepository.findAll()
                .doOnNext(u -> u.setPassword(null))
                .onErrorMap(e -> new RuntimeException());
    }

    public Mono<User> getByUsername(String username){
        return this.userRepository.findByUsername(username);
    }
}
