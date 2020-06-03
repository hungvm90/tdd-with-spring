package com.example.tdddemo.infra.config;

import com.example.tdddemo.domain.register.RegisterRepository;
import com.example.tdddemo.domain.register.RegisterService;
import com.example.tdddemo.infra.register.RegisterDao;
import com.example.tdddemo.infra.register.RegisterRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public RegisterRepository registerRepository(RegisterDao registerDao) {
        return new RegisterRepositoryImpl(registerDao);
    }

    @Bean
    public RegisterService registerService(RegisterRepository registerRepository) {
        return new RegisterService(registerRepository);
    }

}
