package ua.training;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoFactory;
import ua.training.model.dao.OrderDao;
import ua.training.model.dao.UserDao;
import ua.training.model.entity.City;
import ua.training.model.entity.Order;
import ua.training.model.entity.Parcel;
import ua.training.model.entity.User;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class Demo {
    private static final Logger LOGGER = LogManager.getLogger(Demo.class);

    public static void main(String[] args) {
        User user = User.builder()
                .password("123")
                .balance(BigDecimal.valueOf(1000))
                .firstName("Alex")
                .email("black@gmail.com")
                .id(3)
                .login("black123")
                .role(User.Role.USER)
                .lastName("Black")
                .build();


        City vinn = City.builder()
                .latitude(49.2320162F)
                .name("Vinn")
                .nameUk("Вінн")
                .id(3)
                .longitude(28.467975F)
                .build();

        Parcel parcel = Parcel.builder()
                .height(34)
                .length(222)
                .weight(33)
                .width(51)
                .type("Detail")
                .build();

        DaoFactory daoFactory = DaoFactory.getInstance();

        UserDao userDao = daoFactory.createUserDao();
        CityDao cityDao = daoFactory.createCityDao();


        OrderDao orderDao = daoFactory.createOrderDao();

        Order order = Order.builder()
                .requestDate(LocalDate.now().plusDays(1))
                .id(2)
                .userSender(userDao.findById(3).get())
                .cityFrom(cityDao.findById(3).get())
                .cityTo(cityDao.findById(2).get())
                .price(BigDecimal.valueOf(120))
                .parcel(parcel)
                .status(Order.OrderStatus.WAITING_FOR_CONFIRM)
                .build();

        User user1 = userDao.findById(2).get();
        System.out.println(user1);
        user1.setFirstName("Din");
        user1.setPassword("1");
        System.out.println(user1);
        System.out.println(user1.getId());
        System.out.println(userDao.update(user1));


    }

    public static float distFrom(float lat1, float lng1, float lat2, float lng2) {
        double earthRadius = 6371000; //meters
        double dLat = Math.toRadians(lat2 - lat1);
        double dLng = Math.toRadians(lng2 - lng1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLng / 2) * Math.sin(dLng / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return (float) (earthRadius * c);
    }
}
