package ua.training;

import ua.training.model.entity.User;

import java.math.BigDecimal;

public class Demo {
    public static void main(String[] args) {
        User user = User.builder()
                .password("123")
                .balance(BigDecimal.valueOf(1000))
                .firstName("Alex")
                .email("black@gmail.com")
                .id(2)
                .login("black123")
                .role(User.Role.USER)
                .lastName("Black")
                .build();
        System.out.println(user);
    }
}
