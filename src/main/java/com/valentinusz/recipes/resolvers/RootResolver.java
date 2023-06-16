package com.valentinusz.recipes.resolvers;

import com.valentinusz.recipes.model.User;
import com.valentinusz.recipes.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
public class RootResolver {
    private final UserRepository userRepository;

    public RootResolver(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @QueryMapping
    public Iterable<User> users() {
        return userRepository.findAll();
    }
}
