package com.example.tdddemo.domain.register;

import com.example.tdddemo.domain.ApplicationException;
import com.example.tdddemo.domain.ErrorCode;
import com.example.tdddemo.domain.entites.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private RegisterRepository registerRepository;

    private RegisterService registerService;
    @BeforeEach
    void setUp() {
        registerService = new RegisterService(registerRepository);
    }

    @Test
    public void testRegisterNewUser_whenEmailAndMobileIsNotDuplicate_shouldSaveWithMd5HashingPassword() throws Exception {
        Date now = new Date();
        String email = "new_user@gmail.com";
        String mobile = "0123456789";
        User request = new User();
        request.setEmail(email);
        request.setMobile(mobile);
        request.setPassword("123456");
        given(registerRepository.findByEmailOrMobile(email, mobile)).willReturn(null);

        registerService.register(request);

        ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
        verify(registerRepository, times(1)).save(captor.capture());
        assertEquals(request.getEmail(), captor.getValue().getEmail());
        assertEquals(request.getMobile(), captor.getValue().getMobile());
        assertEquals("e10adc3949ba59abbe56e057f20f883e", captor.getValue().getPassword());
        assertTrue(captor.getValue().getCreatedAt().after(now));
        assertEquals(captor.getValue().getCreatedAt(), captor.getValue().getUpdatedAt());
    }

    @Test
    public void testRegisterNewUser_whenEmailOrMobileExist_shouldError() throws Exception {
        try {
            Date now = new Date();
            String email = "new_user@gmail.com";
            String mobile = "0123456789";
            User request = new User();
            request.setEmail(email);
            request.setMobile(mobile);
            request.setPassword("123456");
            given(registerRepository.findByEmailOrMobile(email, mobile)).willReturn(new User());

            registerService.register(request);

            fail("Should ex");
        } catch (ApplicationException e) {
            assertEquals(ErrorCode.RESOURCE_ALREADY_EXIST.getCode(), e.getErrorCode().getCode());
        }
    }
}