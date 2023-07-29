package com.adminServer.user.repository;

import com.adminServer.user.model.SignUp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SignUpRepository extends JpaRepository<SignUp, Long> {
}
