package com.example.demo.domain.Users.repos;

import com.example.demo.domain.Users.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUid(String uid);
}
