package com.github.alisson_martin.rinha.controllers;

import com.github.alisson_martin.rinha.dtos.CreateDTO;
import com.github.alisson_martin.rinha.models.User;
import com.github.alisson_martin.rinha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class ApiController {

  @Autowired
  UserService userService;

  @PostMapping("")
  public ResponseEntity create(@Validated @RequestBody CreateDTO body) {
    userService.create(body);
    return ResponseEntity.status(HttpStatus.CREATED).build();
  }

  @GetMapping("")
  public ResponseEntity list(@RequestParam String t) {
    List<User> users = userService.list(t);

    return ResponseEntity.ok(users);
  }

  @GetMapping("/{uid}")
  public ResponseEntity getById(@PathVariable String uid) {
    User user = userService.getById(uid);

    return ResponseEntity.ok(user);
  }
}
