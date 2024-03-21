package com.PicPaySImplificado.repositories;

import com.PicPaySImplificado.domain.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositories extends JpaRepository<User, Long> {
    Optional<User> findUserByDocument(String document);
    Optional<User> findUseById (Long id);
}
