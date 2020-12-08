package com.hlk.homework.weeks08;

import com.hlk.homework.weeks08.testxa.TransactionConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(TransactionConfiguration.class)
public class TestSsJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestSsJdbcApplication.class, args);
    }

}
