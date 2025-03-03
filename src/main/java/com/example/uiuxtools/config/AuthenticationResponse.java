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
    private Integer userId;

    // Constructor
    public AuthenticationResponse(String token, String message, Integer userId) {
        this.token = token;
        this.message = message;
        this.userId = userId;
    }

    // Static builder method
    public static AuthenticationResponseBuilder builder() {
        return new AuthenticationResponseBuilder();
    }

    // Builder class
    public static class AuthenticationResponseBuilder {
        private String token;
        private String message;
        private Integer userId;

        public AuthenticationResponseBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationResponseBuilder message(String message) {
            this.message = message;
            return this;
        }

        public AuthenticationResponseBuilder userId(Integer userId) {
            this.userId = userId;
            return this;
        }

        public AuthenticationResponse build() {
            return new AuthenticationResponse(token, message, userId);
        }
    }
}
