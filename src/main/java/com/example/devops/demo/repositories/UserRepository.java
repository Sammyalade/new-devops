package com.example.devops.demo.repositories;

import com.example.devops.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("select u from User u where u.email =:email")
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
    }

