package persistence;

import persistence.entities.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import persistence.repositories.RestaurantRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private RestaurantRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

//        repository.deleteAll();
        // save a couple of customers
        repository.save(new Restaurant("El Palacio de la Pizza"));
        repository.save(new Restaurant("El Buen Libro"));

        // fetch all customers
        System.out.println("Restaurants found with findAll():");
        System.out.println("-------------------------------");
        for (Restaurant restaurant : repository.findAll()) {
            System.out.println(restaurant);
        }
        System.out.println();

        // fetch an individual customer
        System.out.println("Restaurant found with findByName('El Buen Libro'):");
        System.out.println("--------------------------------");
        System.out.println(repository.findByName("El Buen Libro"));


    }

}