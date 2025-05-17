package com.example.shopping.exception;

public class EntityNotCreated extends RuntimeException {
    public EntityNotCreated(String userEntityIsNotCreated) {
        super(userEntityIsNotCreated);
    }
}
