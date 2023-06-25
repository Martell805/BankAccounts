package ru.bankaccounts.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.bankaccounts.exception.UserNotFoundException;
import ru.bankaccounts.model.User;
import ru.bankaccounts.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ValidationService validationService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User add(User user) {
        validationService.validateUser(user);
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User oldUser = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userRepository.deleteById(id);
        return oldUser;
    }
}
