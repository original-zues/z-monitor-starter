package com.z.monitor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/z-monitor")
public class ZMonitorAuthController {

    private final ZMonitorProperties properties;

    public ZMonitorAuthController(ZMonitorProperties properties) {
        this.properties = properties;
    }

    public static class LoginRequest {
        public String username;
        public String password;

        public String getUsername() { return username; }
        public void setUsername(String username) { this.username = username; }
        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    @PostMapping("/auth")
    public ResponseEntity<Map<String, Object>> authenticate(@RequestBody LoginRequest req) {
        if (req == null || req.getUsername() == null || req.getPassword() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("errorMessage", "Missing credentials"));
        }

        Optional<ZMonitorProperties.User> match = properties.getUsers().stream()
                .filter(u -> u.getUsername() != null && u.getUsername().equals(req.getUsername()))
                .findFirst();

        if (match.isPresent()) {
            ZMonitorProperties.User user = match.get();
            if (user.getPassword() != null && user.getPassword().equals(req.getPassword())) {
                Map<String, Object> body = new HashMap<>();
                body.put("token", UUID.randomUUID().toString());
                body.put("role", user.getRole() == null ? "ADMIN" : user.getRole());
                body.put("name", user.getName() == null ? user.getUsername() : user.getName());
                return ResponseEntity.ok(body);
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("errorMessage", "Invalid credentials"));
    }
}
