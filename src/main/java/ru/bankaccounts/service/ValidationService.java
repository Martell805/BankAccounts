package ru.bankaccounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.bankaccounts.exception.AccountNotValidException;
import ru.bankaccounts.exception.UserNotValidException;
import ru.bankaccounts.model.Account;
import ru.bankaccounts.model.User;
import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class ValidationService {
    @Value("${app.currencies}")
    private String[] currencies;

    @Value("${app.document-types}")
    private String[] document_types;

    public void validateAccount(Account account) {
        boolean valid = Arrays.stream(currencies).allMatch(
                (currency) -> account.getCurrency().equals(currency)
        );

        if (!valid) {
            throw new AccountNotValidException();
        }
    }

    public void validateUser(User user) {
        boolean valid = Arrays.stream(document_types).allMatch(
                (document_type) -> user.getDocumentType().equals(document_type)
        );

        if (!valid) {
            throw new UserNotValidException();
        }
    }
}
