package com.example.tdddemo.domain.register;

import com.example.tdddemo.domain.entites.User;

public interface RegisterRepository {
    User findByEmailOrMobile(String email, String mobile);

    User save(User user);
}
