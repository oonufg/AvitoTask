package ru.Pavel.Spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.Pavel.Services.TTLService;

@ComponentScan("ru.Pavel")
@PropertySource("classpath:application.yml")
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        TTLService service = new TTLService();
        service.deleteExpiredSegments();
    }
}