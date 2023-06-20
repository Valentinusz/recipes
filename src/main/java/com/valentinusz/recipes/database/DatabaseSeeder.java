package com.valentinusz.recipes.database;

import com.github.javafaker.Faker;
import com.valentinusz.recipes.models.User;
import com.valentinusz.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /** Repository for user model */
    private final UserRepository userRepository;

    /**
     * Constructor.
     *
     * @param faker Faker instance.
     * @param userRepository UserRepository instance.
     * @param bCryptPasswordEncoder BCryptPasswordEncoder instance.
     */
    public DatabaseSeeder(@Autowired Faker faker, @Autowired UserRepository userRepository, @Autowired BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.faker = faker;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        seed();
    }

    /** Seeds the database. */
    public void seed() {
        if (userRepository.count() == 0) {

            User admin = new User("admin username", "admin@recipes.com", bCryptPasswordEncoder.encode("adminpassword"), true);
            userRepository.save(admin);

            for (int i = 0; i < 10; i++) {
                User user = new User(faker.name().fullName(), faker.internet().emailAddress(), bCryptPasswordEncoder.encode("password"), false);
                userRepository.save(user);
            }
        }
    }
}
