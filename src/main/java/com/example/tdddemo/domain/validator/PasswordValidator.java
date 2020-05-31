package com.example.tdddemo.domain.validator;


import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.ErrorCode;
import org.springframework.util.StringUtils;

public class PasswordValidator {
    public static void validate(String password) {
        if (StringUtils.isEmpty(password) || password.length() < 6) {
            throw new ApplicationException(ErrorCode.PASSWORD_INVALID);
        }
    }
}
