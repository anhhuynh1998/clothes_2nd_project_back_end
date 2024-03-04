package com.example.clothes_2nd.model.emun;

public enum Role {
    ROLE_ADMIN("Tao Admin") , ROLE_USER("Tao User đây");

    public final String name;

    Role(String name) {
        this.name = name;
    }
}
