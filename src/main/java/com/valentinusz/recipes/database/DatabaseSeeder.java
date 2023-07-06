package com.valentinusz.recipes.database;

import com.github.javafaker.Faker;
import com.valentinusz.recipes.models.*;
import com.valentinusz.recipes.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * Class responsible for seeding the database with test data.
 * Only active when the application is running in a dev environment.
 */
@Component
@Profile("development")
public class DatabaseSeeder implements ApplicationRunner {
    /** Faker instance for generating fake data. */
    private final Faker faker;

    /** BCryptPasswordEncoder instance. */
    private final PasswordEncoder passwordEncoder;

    /** Repository for user entities. */
    private final UserRepository userRepository;

    /** Repository for dietary restriction entities. */
    private final IngredientAttributeRepository ingredientAttributeRepository;

    /** Repository for ingredient entities. */
    private final IngredientRepository ingredientRepository;

    /** Repository for portioned ingredient entities. */
    private final PortionedIngredientRepository portionedIngredientRepository;

    /** Repository for recipes. */
    private final RecipeRepository recipeRepository;

    /**
     * Constructor.
     *
     * @param faker    Faker instance.
     * @param userRepo UserRepository instance.
     * @param encoder  BCryptPasswordEncoder instance.
     */
    public DatabaseSeeder(@Autowired Faker faker, @Autowired UserRepository userRepo, @Autowired BCryptPasswordEncoder encoder, @Autowired IngredientAttributeRepository restrictionRepo, @Autowired IngredientRepository ingredientRepo, @Autowired PortionedIngredientRepository portionedIngredientRepo, @Autowired RecipeRepository recipeRepo) {
        this.faker = faker;
        this.userRepository = userRepo;
        this.passwordEncoder = encoder;
        this.ingredientAttributeRepository = restrictionRepo;
        this.ingredientRepository = ingredientRepo;
        this.portionedIngredientRepository = portionedIngredientRepo;
        this.recipeRepository = recipeRepo;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (userRepository.count() == 0) {
            seed();
        }
    }

    /** Seeds the database. */
    public void seed() {
        IngredientCategory[] ingredientCategories = IngredientCategory.values();
        ServingSize[] servingSizes = ServingSize.values();
        List<IngredientAttribute> ingredientAttributes = ingredientAttributeRepository.findAll();

        List<User> users = seedUsers(ingredientAttributes);
        userRepository.saveAll(users);

        List<Ingredient> ingredients = seedIngredients(ingredientCategories);
        ingredientRepository.saveAll(ingredients);

        List<Recipe> recipes = seedRecipes(users);
        recipeRepository.saveAll(recipes);

        List<PortionedIngredient> portionedIngredients = seedPortionedIngredients(recipes, ingredients, servingSizes);
        portionedIngredientRepository.saveAll(portionedIngredients);
    }

    private List<User> seedUsers(List<IngredientAttribute> ingredientAttributes) {
        List<User> users = new LinkedList<>();
        User admin = new User();
        admin.setUserName("admin");
        admin.setEmail("admin@recipes.com");
        admin.setEmailVerifiedAt(Instant.now());
        admin.setPassword(passwordEncoder.encode("adminpwd"));
        admin.setIsAdmin(true);
        users.add(admin);

        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUserName(faker.name().username());
            user.setEmail(faker.internet().emailAddress());
            user.setPassword(passwordEncoder.encode("password"));

            // 50% to have a dietary restriction
            if (faker.bool().bool()) {
                int start = faker.random().nextInt(ingredientAttributes.size() - 1);
                // approx. 25% to have 2
                int length = (start != (ingredientAttributes.size() - 1) && faker.random().nextInt(3) == 0 ? 2 : 1);

                user.setDietaryRestrictions(ingredientAttributes.subList(start, start + length));
            }
            users.add(user);
        }

        return users;
    }

    private List<Ingredient> seedIngredients(IngredientCategory[] categories) {
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

    private List<Recipe> seedRecipes(List<User> users) {
        List<Recipe> recipes = new LinkedList<>();

        for (User user : users) {
            for (int i = 0; i < faker.random().nextInt(0, 4); i++) {
                Recipe recipe = new Recipe();
                recipe.setName(faker.food().dish());
                recipe.setPreparationTime(faker.random().nextInt(0, 30));
                recipe.setCookingTime(faker.random().nextInt(0, 90));
                recipe.setInstructions(String.join(" ", faker.lorem().paragraphs(faker.random().nextInt(2, 4))));
                recipe.setAuthor(user);

                recipes.add(recipe);
            }
        }

        return recipes;
    }

    private List<PortionedIngredient> seedPortionedIngredients(List<Recipe> recipes, List<Ingredient> ingredients, ServingSize[] servingSizes) {
        List<PortionedIngredient> portionedIngredients = new LinkedList<>();
        for (Recipe recipe : recipes) {
            var ingredientsToUse = IntStream.generate(() -> faker.random().nextInt(0, ingredients.size() - 1))
                                            .distinct()
                                            .limit(faker.random().nextInt(3, 5))
                                            .mapToObj(index -> {
                                                Ingredient ingredient = ingredients.get(index);
                                                PortionedIngredient portionedIngredient = new PortionedIngredient();
                                                portionedIngredient.setIngredient(ingredient);
                                                portionedIngredient.setRecipe(recipe);
                                                portionedIngredient.setServingSize(servingSizes[faker.random()
                                                                                                     .nextInt(0, servingSizes.length - 1)]);
                                                portionedIngredient.setAmount(faker.number().randomDouble(2, 1, 100));

                                                return portionedIngredient;
                                            })
                                            .toList();

            recipe.setIngredients(ingredientsToUse);
            portionedIngredients.addAll(ingredientsToUse);
        }

        return portionedIngredients;
    }
}
