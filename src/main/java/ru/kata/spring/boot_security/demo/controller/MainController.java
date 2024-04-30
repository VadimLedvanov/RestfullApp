package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @GetMapping("/admin/users")
    public String mainPage() {
        return "pages/index";
    }

    @GetMapping("/user/{id}")
    public String userPage() {
        return "pages/userPage";
    }

}
