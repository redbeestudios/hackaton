package persistence;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import persistence.entities.Dish;
import persistence.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import persistence.repositories.DishRepository;
import persistence.repositories.RestaurantRepository;
import persistence.repositories.UserRepository;


import java.util.ArrayList;
import java.util.ResourceBundle;

@SpringBootApplication
public class Application implements CommandLineRunner {

    Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private DishRepository dishRepository;
    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        restaurantRepository.deleteAll();
        dishRepository.deleteAll();
        userRepository.deleteAll();

        Dish muzzaPalacio = new Dish("Porción Muzzarella","Pizza");
        Dish fainaPalacio = new Dish("Porción Faina","Pizza");

        Dish muzzaKentucky = new Dish("Porción Muzzarella","Pizza");
        Dish napoKentucky = new Dish("Porción Napolitana","Pizza");

        Dish milanga = new Dish("Sandwich Milanesa Ternera Completa","Sandwich");

        Restaurant palacio = new Restaurant("El Palacio de la Pizza",new ArrayList<Dish>());
        Restaurant kentucky = new Restaurant("Kentucky",new ArrayList<Dish>());
        Restaurant buenLibro = new Restaurant("El Buen Libro",new ArrayList<Dish>());

        buenLibro.addDish(milanga);

        palacio.addDish(muzzaPalacio);
        palacio.addDish(fainaPalacio);

        kentucky.addDish(muzzaKentucky);
        kentucky.addDish(napoKentucky);

        restaurantRepository.save(palacio);

        restaurantRepository.save(kentucky);
        restaurantRepository.save(buenLibro);


        // fetch all restaurant
        System.out.println("Restaurants found with findAll():");
        System.out.println("-------------------------------");
        for (Restaurant restaurant : restaurantRepository.findAll()) {
            logger.info(new JSONObject(restaurant).toString());
        }



    }

}