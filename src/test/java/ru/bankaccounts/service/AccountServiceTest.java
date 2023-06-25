package ru.bankaccounts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bankaccounts.exception.AccountNotFoundException;
import ru.bankaccounts.exception.AccountNotValidException;
import ru.bankaccounts.model.Account;
import ru.bankaccounts.repository.AccountRepository;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {
    @InjectMocks
    AccountService accountService;

    @Mock
    AccountRepository accountRepository;

    @Mock
    ValidationService validationService;

    Account account;

    @BeforeEach
    void setUp() {
        account = new Account(
                0L,
                0L,
                "RUB"
        );
    }

    @Test
    void findAllByUserId() {
        when(accountRepository.findAllByUserId(0L)).thenReturn(List.of(account));

        assertThat(List.of(account)).isEqualTo(accountService.findAllByUserId(0L));
    }

    @Test
    void add() {
        when(accountRepository.save(account)).thenReturn(account);
        doNothing().when(validationService).validateAccount(account);

        assertThat(account).isEqualTo(accountService.add(account));
    }

    @Test
    void addInvalid() {
        doThrow(AccountNotValidException.class).when(validationService).validateAccount(account);

        assertThrows(AccountNotValidException.class, () -> accountService.add(account));
    }

    @Test
    void delete() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.ofNullable(account));
        doNothing().when(accountRepository).deleteById(account.getId());

        assertThat(account).isEqualTo(accountService.delete(account.getId()));
    }

    @Test
    void deleteInvalid() {
        when(accountRepository.findById(account.getId())).thenReturn(Optional.empty());

        assertThrows(AccountNotFoundException.class, () -> accountService.delete(account.getId()));
    }
}
