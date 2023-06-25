package ru.bankaccounts.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.bankaccounts.model.Account;
import ru.bankaccounts.service.AccountService;
import java.util.List;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping("all/{userId}")
    public ResponseEntity<List<Account>> getAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(accountService.findAllByUserId(userId));
    }

    @PostMapping()
    public ResponseEntity<Account> postAccount(@RequestBody Account account) {
        return ResponseEntity.ok(accountService.add(account));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Account> postAccount(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.delete(id));
    }
}
