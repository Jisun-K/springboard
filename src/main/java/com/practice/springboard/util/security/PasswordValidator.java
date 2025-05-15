package com.practice.springboard.util.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordValidator {
    private final PasswordEncoder passwordEncoder;

    public boolean matches(String raw, String encoded) {
        if (raw == null || encoded == null) return false;
        return passwordEncoder.matches(raw, encoded);
    }
}
