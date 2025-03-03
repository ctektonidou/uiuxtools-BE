package com.example.uiuxtools.config;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
//@Builder
//@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    private String token;
    private String message;

    // Constructor
    public AuthenticationResponse(String token, String message) {
        this.token = token;
        this.message = message;
    }

    // Static builder method
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    // Builder class
    public static class AuthenticationResponseBuilder {
        private String token;
        private String message;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token, message);
        }
    }
}
