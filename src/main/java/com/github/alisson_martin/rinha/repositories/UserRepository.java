package com.github.alisson_martin.rinha.repositories;

import com.github.alisson_martin.rinha.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
  @Query(value = "SELECT * FROM users u WHERE u.nome LIKE %:search% OR u.apelido LIKE %:search% OR EXISTS (SELECT 1 FROM unnest(u.stack) AS stack_item WHERE stack_item LIKE %:search%)", nativeQuery = true)
  List<User> findUsers(@Param("search") String search);
}