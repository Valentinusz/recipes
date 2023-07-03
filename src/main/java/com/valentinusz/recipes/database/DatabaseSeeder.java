package com.valentinusz.recipes.database;

import com.github.javafaker.Faker;
import com.valentinusz.recipes.models.*;
import com.valentinusz.recipes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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

    /** Repository for user entities. */
    private final UserRepository userRepository;

    /** Repository for dietary restriction entities. */
    private final DietaryRestrictionRepository dietaryRestrictionRepository;

    /** Repository for ingredient entities. */
    private final IngredientRepository ingredientRepository;

    /** Repository for portioned ingredient entities. */
    private final PortionedIngredientRepository portionedIngredientRepository;

    /** Repository for recipes. */
    private final RecipeRepository recipeRepository;

    /**
     * Constructor.
     *
     * @param faker           Faker instance.
     * @param userRepository  UserRepository instance.
     * @param passwordEncoder BCryptPasswordEncoder instance.
     */
    public DatabaseSeeder(@Autowired Faker faker, @Autowired UserRepository userRepository, @Autowired BCryptPasswordEncoder passwordEncoder, @Autowired DietaryRestrictionRepository dietaryRestrictionRepository, @Autowired IngredientRepository ingredientRepository, @Autowired PortionedIngredientRepository portionedIngredientRepository, @Autowired RecipeRepository recipeRepository) {
        this.faker = faker;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.dietaryRestrictionRepository = dietaryRestrictionRepository;
        this.ingredientRepository = ingredientRepository;
        this.portionedIngredientRepository = portionedIngredientRepository;
        this.recipeRepository = recipeRepository;
    }

    @Override
    public void run(String... args) {
        seed();
    }

    /** Seeds the database. */
    public void seed() {
        IngredientCategory[] ingredientCategories = IngredientCategory.values();
        ServingSize[] servingSizes = ServingSize.values();
        List<DietaryRestriction> restrictions = dietaryRestrictionRepository.findAll();

        Collection<User> users = seedUsers(restrictions);
        userRepository.saveAll(users);

        Collection<Ingredient> ingredients = seedIngredients(ingredientCategories);
        ingredientRepository.saveAll(ingredients);

//        int recipeCount = users.size() * 3;
//
//            for (int i = 0; i < faker.random().nextInt(0, 3); i++) {
//                List<Ingredient> ingredients = ingredientRepository.findAll();
//
//                recipeRepository.save(recipe);
//
//                // generate 3-6 ingredients with portions
//
//                recipe.setIngredients(portionedIngredients);
//            }
    }

    private Collection<User> seedUsers(List<DietaryRestriction> dietaryRestrictions) {
        List<User> users = new LinkedList<>();
        User admin = new User();
        admin.setUserName("admin");
        admin.setEmail("admin@recipes.com");
        admin.setEmailVerifiedAt(Instant.now());
        admin.setPassword(passwordEncoder.encode("adminpwd"));
        admin.setIsAdmin(true);

        userRepository.save(admin);
        users.add(admin);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(passwordEncoder.encode("password"));

            // 50% to have a dietary restriction
            if (faker.bool().bool()) {
                int start = faker.random().nextInt(dietaryRestrictions.size() - 1);
                // approx. 25% to have 2
                int length = (start != (dietaryRestrictions.size() - 1) && faker.random().nextInt(3) == 0 ? 2 : 1);

                user.setDietaryRestrictions(dietaryRestrictions.subList(start, start + length));
            }

            users.add(user);
        }

        return users;
    }

    private Collection<Ingredient> seedIngredients(IngredientCategory[] categories) {
        List<Ingredient> ingredients = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            Ingredient ingredient = new Ingredient();
            ingredient.setName(faker.food().ingredient());
            ingredient.setEnergy(faker.random().nextInt(0, 2000));
            ingredient.setCategory(categories[faker.random().nextInt(0, categories.length - 1)]);
            ingredients.add(ingredient);
        }
        return ingredients;
    }

//    private Collection<PortionedIngredient> seedPortionedIngredients(Collection<Ingredient> ingredients, Collection<Recipe> recipes) {
//        List<PortionedIngredient> portionedIngredients = new LinkedList<>();
//        // create 3-4 portioned version for each ingredient
//        for (Ingredient ingredient: ingredients) {
//            PortionedIngredient portionedIngredient = new PortionedIngredient();
////            portionedIngredient.setRecipe(recipe);
//            portionedIngredient.set
//            portionedIngredient.setServingSize(servingSizes[faker.random().nextInt(0, servingSizes.length - 1)]);
//                        portionedIngredient.setAmount(faker.number().randomDouble(2, 1, 100));
//
//                        portionedIngredientRepository.save(portionedIngredient);
//                        portionedIngredients.add(portionedIngredient);
//        }
//
//
//        return portionedIngredients;
//    }

//    private Collection<Recipe> seedRecipe() {
//        List<Recipe> recipes = new LinkedList<>();
//        Recipe recipe = new Recipe();
//        recipe.setName(faker.food().dish());
//        recipe.setPreparationTime(faker.random().nextInt(0, 30));
//        recipe.setCookingTime(faker.random().nextInt(0, 90));
//        recipe.setInstructions(String.join(" ", faker.lorem().paragraphs(faker.random().nextInt(2, 4))));
//        recipe.setAuthor(user);
//        recipes.add(recipe);
//        return recipes;
//    }
}
