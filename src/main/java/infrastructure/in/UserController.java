package infrastructure.in;

import application.CreateUserUseCase;
import domain.entity.User;
import java.util.Scanner;



public class UserController {
    private  CreateUserUseCase createUserUseCase;


 

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    public void start() {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter user name: ");
            String name = scanner.nextLine();

            System.out.println("Enter user email: ");
            String email = scanner.nextLine();

            User user = new User();
            user.setName(name);
            user.setEmail(email);

            createUserUseCase.execute(user);
        }

        System.out.println("User created successfully!");
    }

    public void updateUser(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter id to update: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            System.out.println("Enter  new user name: ");
            String name = scanner.nextLine();

            System.out.println("Enter new user email: ");
            String email = scanner.nextLine();

            User user = new User();
            user.setId(id);
            user.setName(name);
            user.setEmail(email);

            createUserUseCase.executeUpdate(user);
        }

        System.out.println("User update successfully!");
    }
    
    public void deleteUser(){
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter id to delete: ");
            Long id = scanner.nextLong();
            scanner.nextLine();
            
            createUserUseCase.executeDelete(id);
        }
        System.out.println("User delete successfully");
    }
}
