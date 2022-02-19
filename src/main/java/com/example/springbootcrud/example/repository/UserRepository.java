package com.example.springbootcrud.example.repository;

import com.example.springbootcrud.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByNameIgnoreCaseContains(String name);
    List<User> findByEmailIgnoreCase(String email);
}
