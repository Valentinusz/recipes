package com.valentinusz.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Table(name = "ingredient_attributes")
@Data
@NoArgsConstructor
public class IngredientAttribute {
    @Id
    private Integer id;

    @Column(nullable = false, unique = true)
    @Size(min = 3, max = 20)
    private String name;

    @ManyToMany(mappedBy = "dietaryRestrictions")
    @JsonIgnore
    private Collection<User> users;
}
