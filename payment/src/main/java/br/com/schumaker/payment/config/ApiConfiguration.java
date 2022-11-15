package br.com.schumaker.payment.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiConfiguration {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
