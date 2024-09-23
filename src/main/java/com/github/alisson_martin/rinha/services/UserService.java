package com.github.alisson_martin.rinha.services;

import com.github.alisson_martin.rinha.dtos.CreateDTO;
import com.github.alisson_martin.rinha.exceptions.RecordNotFoundException;
import com.github.alisson_martin.rinha.models.User;
import com.github.alisson_martin.rinha.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

  @Autowired
  UserRepository userRepository;

  public User create(CreateDTO data) {
    User user = new User();
    user.setApelido(data.apelido());
    user.setNome(data.nome());
    user.setStack(data.stack());
    user.setNascimento(data.nascimento());

    return userRepository.save(user);
  }

  public User getById(String uid) {
    return userRepository.findById(uid).orElseThrow(()-> new RecordNotFoundException());
  }

  public List<User> list(String search) {
    return userRepository.findUsers(search);
  }

  public long count() {
    return userRepository.count();
  }
}
