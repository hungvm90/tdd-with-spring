package com.example.tdddemo.infra.register;

import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.entites.User;
import com.example.tdddemo.domain.register.RegisterService;
import com.example.tdddemo.domain.validator.EmailValidator;
import com.example.tdddemo.domain.validator.MobileValidator;
import com.example.tdddemo.domain.validator.PasswordValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody RegisterRequest registerRequest) {
        EmailValidator.validate(registerRequest.getEmail());
        MobileValidator.validate(registerRequest.getMobile());
        PasswordValidator.validate(registerRequest.getPassword());
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setMobile(registerRequest.getMobile());
        user.setPassword(registerRequest.getPassword());
        User registeredUser = registerService.register(user);
        registeredUser.setPassword(null);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }

    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity handEx(HttpServletRequest req, ApplicationException ex) {
        return new ResponseEntity(ex.getErrorCode(), HttpStatus.valueOf(ex.getErrorCode().getStatus()));
    }
}