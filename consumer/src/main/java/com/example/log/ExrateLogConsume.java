package com.example.log;

import com.example.coin.data.Candle;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class ExrateLogConsume {

    @Bean
    public Function<List<Candle>, String> generateReport() {
        return test -> "dd";
    }

    @Bean
    @ConditionalOnProperty(value="spring.cloud.function.definition", havingValue = "generateReport|log")
    public Consumer<String> log() {
        return test -> {
            System.out.println("test = " + test);
        };
    }
}
