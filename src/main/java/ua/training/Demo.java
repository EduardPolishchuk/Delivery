package ua.training;

import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.DBPropertyReader;
import ua.training.model.entity.City;
import ua.training.model.entity.Parcel;
import ua.training.model.entity.User;

import java.math.BigDecimal;

public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);

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


        City vinnitsa = City.builder()
                .latitude(49.2320162F)
                .name("Vinnitsa")
                .id(2)
                .longitude(28.467975F)
                .build();

        System.out.println(user);
        System.out.println(vinnitsa);
    }
}
