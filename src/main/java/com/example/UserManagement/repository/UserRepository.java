package com.example.UserManagement.repository;

import com.example.UserManagement.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

public interface UserRepository extends JpaRepository<User, String> {

}
