package com.example.tdddemo.infra.register;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterDao extends JpaRepository<UserModel, Integer> {
    Optional<UserModel> findByEmailOrMobile(String email, String mobile);
}
