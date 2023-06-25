package ru.bankaccounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bankaccounts.exception.AccountNotFoundException;
import ru.bankaccounts.model.Account;
import ru.bankaccounts.repository.AccountRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final ValidationService validationService;

    public List<Account> findAllByUserId(Long userId) {
        return accountRepository.findAllByUserId(userId);
    }

    public Account add(Account account) {
        validationService.validateAccount(account);
        return accountRepository.save(account);
    }

    public Account delete(Long id) {
        Account oldAccount = accountRepository.findById(id).orElseThrow(AccountNotFoundException::new);
        accountRepository.deleteById(id);
        return oldAccount;
    }
}
