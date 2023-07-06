package com.valentinusz.recipes.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "ingredient_attributes")
public class IngredientAttribute {
    @Id
    private Integer id;

    @Column
    @Size(min = 3, max = 20)
    private String name;

    @ManyToMany(mappedBy = "dietaryRestrictions")
    @JsonIgnore
    private Collection<User> users = new HashSet<>();

    public IngredientAttribute() {}

    public IngredientAttribute(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<User> getUsers() {
        return users;
    }

    public void setUsers(Collection<User> users) {
        this.users = users;
    }
}
