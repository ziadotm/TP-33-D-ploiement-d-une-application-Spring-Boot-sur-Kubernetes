package com.medori42.platformcore.api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
@RestController
public class GreetingApiController {
    private static final String GREETING_KEY = "greeting";
    private static final String STATUS_KEY = "status";
    private static final String STATUS_OK = "OK";
    @Value("${APP_GREETING:Welcome from the default value}")
    private String greetingMessage;
    public GreetingApiController() {
    }
    @GetMapping("/api/greet")
    public Map<String, String> getGreeting() {
        return buildGreetingResponse();
    }
    private Map<String, String> buildGreetingResponse() {
        Map<String, String> response = new HashMap<>();
        response.put(GREETING_KEY, fetchGreetingMessage());
        response.put(STATUS_KEY, STATUS_OK);
        return response;
    }
    private String fetchGreetingMessage() {
        return this.greetingMessage;
    }
}