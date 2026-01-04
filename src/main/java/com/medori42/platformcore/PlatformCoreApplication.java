package com.medori42.platformcore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class PlatformCoreApplication {
    public static void main(final String[] args) {
        PlatformCoreApplication app = new PlatformCoreApplication();
        app.start(args);
    }
    private void start(final String[] startupArgs) {
        SpringApplication.run(PlatformCoreApplication.class, startupArgs);
    }
    public PlatformCoreApplication() {
    }
}