package com.example.tdddemo.domain.validator;

import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.ErrorCode;
import org.springframework.util.StringUtils;

import java.util.regex.Pattern;

public class MobileValidator {
    private static String mobilePattern = "^[0-9]{10,11}$";
    private static Pattern pattern = Pattern.compile(mobilePattern);

    public static void validate(String mobile) {
        if (StringUtils.isEmpty(mobile) || !pattern.matcher(mobile).find()) {
            throw new ApplicationException(ErrorCode.MOBILE_INVALID);
        }
    }
}
