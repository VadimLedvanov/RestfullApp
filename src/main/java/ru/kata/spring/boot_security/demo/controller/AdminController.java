package ru.kata.spring.boot_security.demo.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.dto.UserDTO;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "pages/admin";
    }

    @GetMapping("/admin/users")
    public List<UserDTO> allUsersPage() {
        return adminService.findAll().stream().map(this::convertToUserDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/admin/users/{id}")
    public UserDTO show(@PathVariable("id") Long id) {
        return convertToUserDTO(adminService.findById(id).orElse(null));
    }

    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        adminService.deleteById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/admin/users")
    public ResponseEntity<HttpStatus> create(@RequestBody UserDTO userDTO) {
        adminService.save(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/admin/users")
    public ResponseEntity<HttpStatus> update(@RequestBody UserDTO userDTO) {
        adminService.save(convertToUser(userDTO));
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setPassword(user.getPassword());
        userDTO.setAge(user.getAge());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getUsername());
        user.setPassword(userDTO.getPassword());
        user.setAge(userDTO.getAge());
        user.setRole(userDTO.getRoles());
        return user;
    }
}
