package com.valentinusz.recipes.repositories;

import com.valentinusz.recipes.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
