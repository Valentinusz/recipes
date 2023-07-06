package com.valentinusz.recipes.controllers;

import com.valentinusz.recipes.models.IngredientAttribute;
import com.valentinusz.recipes.models.User;
import com.valentinusz.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users")
    public Iterable<User> index() {
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );
    }

    // TODO add this to a nested controller
    @GetMapping("/users/{id}/restrictions")
    public ResponseEntity<Collection<IngredientAttribute>> getDietaryRestrictionsOfUser(@PathVariable Long id) {
        User user = userRepository.findUserByIdWithDietaryRestrictions(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found!")
        );

        return ResponseEntity.ok().body(user.getDietaryRestrictions());
    }
}
