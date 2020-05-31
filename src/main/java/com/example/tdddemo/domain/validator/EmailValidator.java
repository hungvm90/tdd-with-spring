package com.example.tdddemo.domain.validator;

import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class EmailValidator {

    private static String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)+$";
    private static Pattern pattern = Pattern.compile(emailPattern);

    public static void validate(String email) {
        if (StringUtils.isEmpty(email) || !pattern.matcher(email).find()) {
            throw new ApplicationException(ErrorCode.EMAIL_INVALID);
        }
    }
}
