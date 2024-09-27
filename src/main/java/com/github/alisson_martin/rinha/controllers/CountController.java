package com.github.alisson_martin.rinha.controllers;

import com.github.alisson_martin.rinha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountController {

  @Autowired
  UserService userService;

  @GetMapping("/contagem-pessoas")
  public ResponseEntity countUsers() {
    long usersCount = userService.count();

    return ResponseEntity.ok(usersCount);
  }
}
