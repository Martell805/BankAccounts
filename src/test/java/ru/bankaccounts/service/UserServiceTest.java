package ru.bankaccounts.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.bankaccounts.exception.UserNotFoundException;
import ru.bankaccounts.exception.UserNotValidException;
import ru.bankaccounts.model.User;
import ru.bankaccounts.repository.UserRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;

    @Mock
    ValidationService validationService;

    User user;

    @BeforeEach
    void setUp() {
        user = new User(
                0L,
                "User",
                "User",
                "User",
                "passport-ru",
                "12345",
                LocalDate.of(2000, 1, 1)
        );
    }

    @Test
    void findAllByUserId() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        assertThat(List.of(user)).isEqualTo(userService.findAll());
    }

    @Test
    void add() {
        when(userRepository.save(user)).thenReturn(user);
        doNothing().when(validationService).validateUser(user);

        assertThat(user).isEqualTo(userService.add(user));
    }

    @Test
    void addInvalid() {
        doThrow(UserNotValidException.class).when(validationService).validateUser(user);

        assertThrows(UserNotValidException.class, () -> userService.add(user));
    }

    @Test
    void delete() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.ofNullable(user));
        doNothing().when(userRepository).deleteById(user.getId());

        assertThat(user).isEqualTo(userService.delete(user.getId()));
    }

    @Test
    void deleteInvalid() {
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.delete(user.getId()));
    }
}
