package com.wingbank.core.security.dto;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String refreshToken;
    private String username;

    // Constructors
    public JwtResponse() {}

    public JwtResponse(String accessToken, String refreshToken, String username) {
        this.token = accessToken;
        this.refreshToken = refreshToken;
        this.username = username;
    }

    // Getters and Setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getRefreshToken() { return refreshToken; }
    public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }
}
