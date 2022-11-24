package com.springboot.rest.repository;

import com.springboot.rest.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, String> {
    public User findByUserId(String userId);

}
