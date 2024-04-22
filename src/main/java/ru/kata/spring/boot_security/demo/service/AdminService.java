package ru.kata.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface AdminService {

    List<User> findAll();


    Optional<User> findById(Long id);


    void deleteById(Long id);


    void save(User user, List<Long> selectedRoles);


    void update(User user, List<Long> selectedRoleId);


    User findByUsername(String username);
}
