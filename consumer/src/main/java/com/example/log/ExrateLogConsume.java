package com.example.log;

import com.example.data.ExRate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;

@Configuration
public class ExrateLogConsume {

    @Bean
    public Consumer<List<ExRate>> log() {
        return exRates -> System.out.println("exRates = " + exRates);
    }
}
