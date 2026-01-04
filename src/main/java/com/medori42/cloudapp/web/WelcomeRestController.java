package com.medori42.cloudapp.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
@RestController
public class WelcomeRestController {
    private static final String RESPONSE_MESSAGE_KEY = "message";
    private static final String RESPONSE_STATUS_KEY = "status";
    private static final String STATUS_SUCCESS_VALUE = "OK";
    @Value("${APP_MESSAGE:Bienvenue depuis la valeur par defaut}")
    private String welcomeMessageContent;
    public WelcomeRestController() {
    }
    @GetMapping("/api/hello")
    public Map<String, String> retrieveWelcomeMessage() {
        return buildResponsePayload();
    }
    private Map<String, String> buildResponsePayload() {
        Map<String, String> responsePayload = new HashMap<>();
        responsePayload.put(RESPONSE_MESSAGE_KEY, getWelcomeMessageContent());
        responsePayload.put(RESPONSE_STATUS_KEY, STATUS_SUCCESS_VALUE);
        return responsePayload;
    }
    private String getWelcomeMessageContent() {
        return this.welcomeMessageContent;
    }
}