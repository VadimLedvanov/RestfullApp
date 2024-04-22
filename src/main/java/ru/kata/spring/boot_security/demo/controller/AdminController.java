package ru.kata.spring.boot_security.demo.controller;

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
import org.springframework.web.bind.annotation.RequestParam;


import org.springframework.web.bind.annotation.RestController;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;
import ru.kata.spring.boot_security.demo.util.UserIncorrectData;
import ru.kata.spring.boot_security.demo.util.UserNotFoundException;


import java.util.List;
import java.util.Optional;



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
    public List<User> allUsersPage(Model model) {
        return adminService.findAll();
    }

    @GetMapping("/admin/user/{id}")
    public User show(@PathVariable("id") Long id) {
        Optional<User> user = adminService.findById(id);

        if (user.isEmpty()) {
            throw new UserNotFoundException("There is no user with ID = " + id);
        }

        return user.get();
    }

    @DeleteMapping("/admin/user")
    public String deleteUser(@RequestParam("id") Long id) {
        adminService.deleteById(id);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/new")
    public String addUserPage(Model model) {
        User user = new User();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User person = (User) authentication.getPrincipal();

        boolean isAdmin = person.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("person", person);
        model.addAttribute("addUser", user);

        return "pages/newUser";
    }

    @PostMapping("/admin/users")
    public String create(@RequestParam(value = "selectedRoles", required = false)
                         List<Long> selectedRoleId,
                         @RequestBody User user) {
        adminService.save(user, selectedRoleId);
        return "redirect:/admin/users";
    }

//    @GetMapping("/admin/users/edit")
//    public String editUser(@RequestParam("id") Long id,
//                           Model model) {
//        Optional<User> user = adminService.findById(id);
//
//        if (user.isEmpty()) {
//            return "pages/noUser";
//        }
//
//        model.addAttribute("editUser", user.get());
//        return "pages/edit";
//    }

    @PutMapping("/admin/users")
    public User update(@RequestBody User user) {
        adminService.update(user, List.of());
        return user;
    }


}
