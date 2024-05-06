package ru.kata.spring.boot_security.demo.service;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<User> findAll();


    Optional<User> findById(Long id);


    void deleteById(Long id);


    void save(User user);

    void update(User user);


    User findByUsername(String username);
}
