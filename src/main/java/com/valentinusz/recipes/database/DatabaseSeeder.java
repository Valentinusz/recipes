package com.valentinusz.recipes.database;

import com.github.javafaker.Faker;
import com.valentinusz.recipes.models.DietaryRestriction;
import com.valentinusz.recipes.models.User;
import com.valentinusz.recipes.repositories.DietaryRestrictionRepository;
import com.valentinusz.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.StreamSupport;

/**
 * Class responsible for seeding the database with test data.
 * Only active when the application is running in a dev environment.
 */
@Component
@Profile("development")
public class DatabaseSeeder implements CommandLineRunner {
    /** Faker instance for generating fake data. */
    private final Faker faker;

    /** BCryptPasswordEncoder instance. */
    private final PasswordEncoder passwordEncoder;

    /** Repository for user model */
    private final UserRepository userRepository;

    private final DietaryRestrictionRepository dietaryRestrictionRepository;

    /**
     * Constructor.
     *
     * @param faker Faker instance.
     * @param userRepository UserRepository instance.
     * @param passwordEncoder BCryptPasswordEncoder instance.
     */
    public DatabaseSeeder(@Autowired Faker faker, @Autowired UserRepository userRepository, @Autowired BCryptPasswordEncoder passwordEncoder, @Autowired DietaryRestrictionRepository dietaryRestrictionRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.dietaryRestrictionRepository = dietaryRestrictionRepository;
    }

    @Override
    public void run(String... args) {
        seed();
    }

    /** Seeds the database. */
    public void seed() {
        if (userRepository.count() == 0) {
            List<DietaryRestriction> restrictions = StreamSupport.stream(dietaryRestrictionRepository.findAll().spliterator(), false).toList();

            User admin = new User("admin username", "admin@recipes.com", passwordEncoder.encode("adminpassword"), true);
            userRepository.save(admin);

            for (int i = 0; i < 10; i++) {
                User user = new User(faker.name().fullName(), faker.internet().emailAddress(), passwordEncoder.encode("password"), false);

                if (faker.bool().bool()) {
                    System.out.println("asd");
                    int start = faker.random().nextInt(0, restrictions.size() - 1);
                    int length = start != (restrictions.size() - 1) && faker.random().nextInt(0, 3) == 0 ? 2 : 1;

                    user.setDietaryRestrictions(restrictions.subList(start, start + length));
                }

                userRepository.save(user);
            }
        }
    }
}
