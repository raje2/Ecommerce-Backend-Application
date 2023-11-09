package com.ecommerce.holder;

public class TokenHolder {
    private String token;

    public TokenHolder() {
        this.token = null;
    }

    public TokenHolder(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean hasToken() {
        return token != null;
    }

    public void clearToken() {
        token = null;
    }
}
