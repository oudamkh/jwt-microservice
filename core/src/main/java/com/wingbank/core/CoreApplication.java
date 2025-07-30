package com.wingbank.core;

import com.wingbank.core.security.KeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
        //KeyGenerator.printRSAKeyPairs();
    }

}
