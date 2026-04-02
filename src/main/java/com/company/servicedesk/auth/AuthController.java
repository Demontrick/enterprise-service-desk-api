package com.company.servicedesk.auth;

import com.company.servicedesk.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    // Hardcoded users simulating a user store
    // In production this would come from a database
    private static final Map<String, String[]> USERS = Map.of(
        "admin",      new String[]{"admin123",  "ADMIN"},
        "technician", new String[]{"tech123",   "TECHNICIAN"},
        "viewer",     new String[]{"viewer123", "VIEWER"}
    );

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        String[] userData = USERS.get(request.getUsername());

        if (userData == null || !userData[0].equals(request.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }

        String token = jwtUtil.generateToken(request.getUsername(), userData[1]);
        return ResponseEntity.ok(new AuthResponse(token, userData[1]));
    }
}