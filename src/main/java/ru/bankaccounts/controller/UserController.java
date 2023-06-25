package ru.bankaccounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bankaccounts.model.User;
import ru.bankaccounts.service.UserService;
import java.util.List;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("all")
    public ResponseEntity<List<User>> getAllByUserId() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PostMapping()
    public ResponseEntity<User> postUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.add(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<User> postUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.delete(id));
    }
}
