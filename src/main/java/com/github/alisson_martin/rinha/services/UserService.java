package com.github.alisson_martin.rinha.services;

import com.github.alisson_martin.rinha.dtos.CreateDTO;
import com.github.alisson_martin.rinha.exceptions.RecordNotFoundException;
import com.github.alisson_martin.rinha.models.User;
import com.github.alisson_martin.rinha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public Mono<UUID> create(CreateDTO data) {
    User user = new User();
    user.setId(UUID.randomUUID());
    user.setApelido(data.apelido());
    user.setNome(data.nome());
    user.setStack(data.stack());
    user.setNascimento(data.nascimento());

    return userRepository.save(user)
            .doOnSuccess(savedUser -> System.out.println("Usuário salvo com sucesso: " + savedUser))
            .doOnError(error -> System.err.println("Erro ao salvar usuário: " + error.getMessage()))
            .map(User::getId);

  }

  public Mono<User> getById(UUID uid) {
    return userRepository.findById(uid).switchIfEmpty(Mono.error(new RecordNotFoundException()));
  }

  public Flux<User> list(String search) {
    String searchQuery = "%" + search + "%";
    return userRepository.findUsers(searchQuery);
  }

  public Mono<Long> count() {
    return userRepository.count();
  }
}
