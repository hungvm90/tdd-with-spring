package com.example.tdddemo.register;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<User> registerNewUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setMobile(registerRequest.getMobile());
        user.setPassword(registerRequest.getPassword());
        User registeredUser = registerService.register(user);
        registeredUser.setPassword(null);
        return new ResponseEntity<>(registeredUser, HttpStatus.OK);
    }
}