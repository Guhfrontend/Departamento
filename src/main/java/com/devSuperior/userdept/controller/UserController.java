package com.devSuperior.userdept.controller;

import com.devSuperior.userdept.model.User;
import com.devSuperior.userdept.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> getAll(){
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable Long id){
        return userRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping ResponseEntity<User> post(@RequestBody User user){
        return ResponseEntity.status(HttpStatus.CREATED).body(userRepository.save(user));
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user){
        return userRepository.findById(user.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(userRepository.save(user))) .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Long id){
        if (userRepository.findById(id).isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        userRepository.deleteById(id);
    }
}
