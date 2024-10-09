package com.github.alisson_martin.rinha.controllers;

import com.github.alisson_martin.rinha.dtos.CreateDTO;
import com.github.alisson_martin.rinha.models.User;
import com.github.alisson_martin.rinha.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pessoas")
public class ApiController {

  @Autowired
  UserService userService;

  @PostMapping("")
  public Mono<ResponseEntity<Void>> create(@Validated @RequestBody CreateDTO body) {
    if (body.apelido() == null || body.apelido().isBlank() || body.apelido().length() > 32 || body.nome() == null || body.nome().isBlank() || body.nome().length() > 100 || nascimentoIsInvalid(body.nascimento())) {
      return Mono.just(ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build());
    }

    return userService.create(body)
            .map(uid -> {
              HttpHeaders headers = new HttpHeaders();
              headers.add(HttpHeaders.LOCATION, "/pessoas/" + uid);
              return ResponseEntity.status(HttpStatus.CREATED).headers(headers).build();
            });
  }

  @GetMapping("")
  @ResponseStatus(HttpStatus.OK)
  public Flux<User> list(@RequestParam String t) {
    return userService.list(t);
  }

  @GetMapping("/{uid}")
  @ResponseStatus(HttpStatus.OK)
  public Mono<User> getById(@PathVariable UUID uid) {
    return userService.getById(uid);
  }

  private boolean nascimentoIsInvalid(String nascimento) {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    sdf.setLenient(false);
    try {
      sdf.parse(nascimento);
      return false;
    } catch (ParseException e) {
      return true;
    }
  }
}
