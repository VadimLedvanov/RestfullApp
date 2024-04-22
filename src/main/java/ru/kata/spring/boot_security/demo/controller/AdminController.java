package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.AdminService;


import java.util.List;
import java.util.Optional;



@Controller
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
    public String allUsersPage(Model model) {
        List<User> list = adminService.findAll();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User person = (User) authentication.getPrincipal();
        model.addAttribute("users", list);
        model.addAttribute("person", person);
        model.addAttribute("editUser", new User());

        return "pages/index";
    }

    @GetMapping("/admin/user")
    public String show(@RequestParam("id") Long id,
                       Model model) {
        Optional<User> user = adminService.findById(id);

        if (user.isEmpty()) {
            return "pages/noUser";
        }

        model.addAttribute("user", user.get());
        return "pages/show";
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
    public String create(@ModelAttribute("user") User user,
                         @RequestParam(value = "selectedRoles", required = false)
                         List<Long> selectedRoleId) {
        adminService.save(user, selectedRoleId);
        return "redirect:/admin/users";
    }

    @GetMapping("/admin/users/edit")
    public String editUser(@RequestParam("id") Long id,
                           Model model) {
        Optional<User> user = adminService.findById(id);

        if (user.isEmpty()) {
            return "pages/noUser";
        }

        model.addAttribute("editUser", user.get());
        return "pages/edit";
    }

    @PatchMapping("/admin/user/edit")
    public String update(@ModelAttribute("editUser") User user,
                         @RequestParam(value = "selectedRoles", required = false)
                         List<Long> selectedRoleId) {
        adminService.update(user, selectedRoleId);
        return "redirect:/admin/users";
    }
}
