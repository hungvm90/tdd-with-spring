package com.example.tdddemo.infra.register;

import com.example.tdddemo.domain.entites.User;
import com.example.tdddemo.domain.register.RegisterRepository;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

public class RegisterRepositoryImpl implements RegisterRepository {
    private final RegisterDao registerDao;

    public RegisterRepositoryImpl(RegisterDao registerDao) {
        this.registerDao = registerDao;
    }

    @Override
    public User findByEmailOrMobile(String email, String mobile) {
        return registerDao.findByEmailOrMobile(email, mobile)
                .map(this::fromDB).orElse(null);
    }

    @Override
    public User save(User user) {
        UserModel userModel = registerDao.save(toDB(user));
        user.setId(userModel.getId());
        return user;
    }

    private UserModel toDB(User user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }

    private User fromDB(UserModel userModel) {
        User user = new User();
        BeanUtils.copyProperties(userModel, user);
        return user;
    }
}
