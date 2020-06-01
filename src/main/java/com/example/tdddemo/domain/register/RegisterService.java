package com.example.tdddemo.domain.register;

import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.ErrorCode;
import com.example.tdddemo.domain.entites.User;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.MessageDigest;
import java.util.Date;

public class RegisterService {
    private final RegisterRepository registerRepository;

    public RegisterService(RegisterRepository registerRepository) {
        this.registerRepository = registerRepository;
    }

    public User register(User request) {
        Date now = new Date();
        User existedUser = registerRepository.findByEmailOrMobile(request.getEmail(), request.getMobile());
        if (existedUser != null) {
            throw new ApplicationException(ErrorCode.RESOURCE_ALREADY_EXIST);
        }
        request.setCreatedAt(now);
        request.setUpdatedAt(now);
        request.setPassword(DigestUtils.md5Hex(request.getPassword()));
        return registerRepository.save(request);
    }
}
