package com.github.alisson_martin.rinha.repositories;

import com.github.alisson_martin.rinha.models.User;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

import java.util.UUID;


public interface UserRepository extends ReactiveCrudRepository<User, UUID> {
  @Query(value = "SELECT * FROM users WHERE concatsearch ILIKE :search")
  Flux<User> findUsers(@Param("search") String search);
}