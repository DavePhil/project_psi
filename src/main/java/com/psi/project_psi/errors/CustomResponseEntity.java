package com.psi.project_psi.errors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class CustomResponseEntity<T> extends ResponseEntity<T> {

    private static final String ERROR_MESSAGES_FILE = "src/main/java/com/psi/project_psi/errors/error_messages.json";
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Map<String, Map<String, String>> errorMessages;

    static {
        errorMessages = loadMessages();
    }
    public CustomResponseEntity(HttpStatusCode status) {
        super(status);
    }

    public CustomResponseEntity(T body, HttpStatusCode status) {
        super(body, status);
    }


    public static CustomResponseEntity<?> fromKey(String key, HttpStatusCode status) {
        Map<String, String> message = resolveMessage(key);
        return new CustomResponseEntity<>(Map.of("Message", message), status);
    }

//    public static CustomResponseEntity<?> fromKey(String key, HttpStatusCode status, Object... args) {
//        String message = resolveMessage(key, args);
//        return new CustomResponseEntity<>(message, status);
//    }

    private static Map<String, String> resolveMessage(String key) {
        Map<String, String> messages = errorMessages.get(key);
        if (messages == null) {
            return Map.of("Message","Unknown message");
        }
        return messages;
    }
    private static Map<String, Map<String, String>> loadMessages() {
        try {
            File file = new File(CustomResponseEntity.ERROR_MESSAGES_FILE);
            Map<String, Map<String, String>> messages = objectMapper.readValue(file, Map.class);
            return messages;
        } catch (IOException e) {
            // Handle exception
            return Map.of();
        }
    }

//    private static Map<String, Object> resolveMessage(String key, Object... args) {
//        return (resolveMessage(key), args);
//    }
}
