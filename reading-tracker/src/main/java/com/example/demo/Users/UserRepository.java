package com.example.demo.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Users.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {
	Users findByUsername(String username);
}
