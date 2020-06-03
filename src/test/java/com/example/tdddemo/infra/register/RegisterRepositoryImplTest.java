package com.example.tdddemo.infra.register;

import com.example.tdddemo.domain.entites.User;
import com.example.tdddemo.domain.register.RegisterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RegisterRepositoryImplTest {

    @Autowired
    private RegisterDao registerDao;
    private RegisterRepository registerRepository;
    @BeforeEach
    void setUp() {
        registerRepository = new RegisterRepositoryImpl(registerDao);
    }

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setMobile("0123456789");
        user.setEmail("a@gmail.com");
        user.setPassword("hashed_password");
        user.setCreatedAt(new Date());

        User savedUser = registerRepository.save(user);

        assertTrue(savedUser.getId() > 0);
        user.setId(savedUser.getId());
        assertEquals(user, savedUser);
        User getBack = registerRepository.findByEmailOrMobile(user.getEmail(), "");
        assertEquals(user, getBack);
        getBack = registerRepository.findByEmailOrMobile("", user.getMobile());
        assertEquals(user, getBack);
    }
}