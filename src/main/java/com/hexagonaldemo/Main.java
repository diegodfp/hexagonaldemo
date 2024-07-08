package com.hexagonaldemo;

import application.CreateUserUseCase;
import domain.service.UserService;
import infrastructure.in.UserController;
import infrastructure.out.UserRepository;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserRepository();
        CreateUserUseCase createUserUseCase = new CreateUserUseCase(userService);
        UserController consoleAdapter = new UserController(createUserUseCase);

        consoleAdapter.deleteUser();
    }
}