package ru.bankaccounts.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bankaccounts.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}