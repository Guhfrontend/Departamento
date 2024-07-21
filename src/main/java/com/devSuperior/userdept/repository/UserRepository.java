package com.devSuperior.userdept.repository;

import com.devSuperior.userdept.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
