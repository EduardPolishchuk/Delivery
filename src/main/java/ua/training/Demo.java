package ua.training;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import ua.training.model.dao.CityDao;
import ua.training.model.dao.DaoFactory;
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
                .id(2)
                .login("black123")
                .role(User.Role.USER)
                .lastName("Black")
                .build();


        City vinn = City.builder()
                .latitude(49.2320162F)
                .name("Vinn")
                .nameUk("Вінн")
                .id(2)
                .longitude(28.467975F)
                .build();

        Parcel parcel = Parcel.builder()
                .height(24)
                .length(34)
                .weight(51)
                .width(44)
                .type("Clothe")
                .build();

        Order order = Order.builder()
                .receivingDate(LocalDate.now())
                .id(2)
                .userSender(user)
                .cityFrom(vinn)
                .cityTo(City.builder().name("Kiev").build())
                .price(BigDecimal.valueOf(120))
                .parcel(parcel)
                .build();

        DaoFactory daoFactory = DaoFactory.getInstance();

        UserDao userDao = daoFactory.createUserDao();

        CityDao cityDao = daoFactory.createCityDao();
        List<City> list = cityDao.findAll();
        int k=0;
        City newCity = cityDao.findById(1).get();
        for (City city : list) {
            System.out.println(city.getName() + " - " + newCity.getName());
            System.out.println(distFrom(city.getLatitude(),city.getLongitude()
                    ,newCity.getLatitude(),newCity.getLongitude())/1000);
            System.out.println("=================================");
        }


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
