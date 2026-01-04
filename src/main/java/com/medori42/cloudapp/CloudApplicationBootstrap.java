package com.medori42.cloudapp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class CloudApplicationBootstrap {
    public CloudApplicationBootstrap() {
    }
    public static void main(final String[] applicationArguments) {
        initializeApplication(applicationArguments);
    }
    private static void initializeApplication(final String[] runtimeArguments) {
        SpringApplication.run(CloudApplicationBootstrap.class, runtimeArguments);
    }
}