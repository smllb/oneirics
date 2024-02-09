package com.lucid.oneiric;

import com.lucid.oneiric.repository.DatabaseRepository;
import com.lucid.oneiric.services.SecurePasswordService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@SpringBootApplication
public class OneiricApplication {

    @RequestMapping("/")
    String home() {
        return "Hello World";
    }

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(OneiricApplication.class, args);
        System.out.println("ab");
        DatabaseRepository database = new DatabaseRepository();
        database.establishConnection();
        SecurePasswordService encoder = new SecurePasswordService();
        System.out.println(encoder.encodeRawPassword("paozinho"));
//        database.spawnDefaultDatabase();
    }

}
