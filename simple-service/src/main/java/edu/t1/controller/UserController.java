package edu.t1.controller;

import edu.t1.model.User;
import edu.t1.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping
    public String getSomething() {
        return "Example";
    }

    @GetMapping("/{name}")
    public User getById(@PathVariable String name) {
        return service.getUserByName(name);
    }

}
